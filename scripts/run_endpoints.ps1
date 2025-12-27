$ErrorActionPreference = 'Stop'
$logFile = "endpoints.log"
"=== Endpoint run: $(Get-Date -Format o) ===" | Out-File -FilePath $logFile
Function Log($obj){
    if ($null -eq $obj) { "<null>" | Out-File -FilePath $logFile -Append; return }
    $obj | ConvertTo-Json -Depth 10 | Out-File -FilePath $logFile -Append
}
Function PostJson($url, $body){
    $json = $body | ConvertTo-Json -Depth 10
    Try {
        $resp = Invoke-RestMethod -Uri $url -Method Post -Body $json -ContentType 'application/json'
        Log $resp
        return $resp
    } Catch {
        $_ | Out-String | Out-File -FilePath $logFile -Append
        return $null
    }
}
Function GetJson($url){
    Try {
        $resp = Invoke-RestMethod -Uri $url -Method Get
        Log $resp
        return $resp
    } Catch {
        $_ | Out-String | Out-File -FilePath $logFile -Append
        return $null
    }
}
# Wait for server on port 8080
$timeout = 60
$start = Get-Date
while (-not (Test-NetConnection -ComputerName 'localhost' -Port 8080 -WarningAction SilentlyContinue).TcpTestSucceeded) {
    if ((Get-Date) -gt $start.AddSeconds($timeout)) { "Server did not start within $timeout seconds" | Out-File -FilePath $logFile -Append; exit 2 }
    Start-Sleep -Seconds 1
}
# Begin calls
$base = 'http://localhost:8080'
"POST /api/roles" | Out-File -FilePath $logFile -Append
$role = PostJson "$base/api/roles" @{ roleName = 'Admin' }
"GET /api/roles" | Out-File -FilePath $logFile -Append
$roles = GetJson "$base/api/roles"
# Create a user - adapt fields if server expects different ones
"POST /api/users" | Out-File -FilePath $logFile -Append
$userPayload = @{
    username = 'testuser'
    passwordHash = 'Test@123'
    email = 'test@example.com'
    phone = '1234567890'
    status = 'ACTIVE'
}
$user = PostJson "$base/api/users" $userPayload
# Create a profile for the user
"POST /api/profiles" | Out-File -FilePath $logFile -Append
$profilePayload = @{
    firstName = 'John'
    lastName = 'Doe'
    address = '123 Main St'
    user = @{ id = ($user.id -as [int]) }
}
$profile = PostJson "$base/api/profiles" $profilePayload
# Get profiles
"GET /api/profiles" | Out-File -FilePath $logFile -Append
$allProfiles = GetJson "$base/api/profiles"
# Get profile by id if created
if ($profile -ne $null -and $profile.id) { "GET /api/profiles/$($profile.id)" | Out-File -FilePath $logFile -Append; $p = GetJson "$base/api/profiles/$($profile.id)" }
# Create a user-role mapping (if endpoint exists)
"POST /api/userRoleMappings" | Out-File -FilePath $logFile -Append
$urmPayload = @{
    user = @{ id = ($user.id -as [int]) }
    role = @{ id = ($role.id -as [int]) }
}
$urm = PostJson "$base/api/userRoleMappings" $urmPayload
"GET /api/userRoleMappings" | Out-File -FilePath $logFile -Append
$urms = GetJson "$base/api/userRoleMappings"
"=== Endpoint run complete: $(Get-Date -Format o) ===" | Out-File -FilePath $logFile -Append
Write-Output "Endpoint script finished; log saved to $logFile" 
