<?php
// login.php

session_start();

// Подключение конфигурации и функций
require_once 'db_config.php';
require_once 'functions.php';

// Получаем данные из формы
$username = isset($_POST['username']) ? $_POST['username'] : '';
$password = isset($_POST['password']) ? $_POST['password'] : '';
$ip_address = $_SERVER['REMOTE_ADDR'];  // Получаем IP-адрес пользователя

// Проверяем, заблокирован ли IP
if (is_ip_blocked($mysqli, $ip_address)) {
    die("Your IP is temporarily blocked due to too many failed login attempts.");
}

// Запрос к базе данных для получения данных пользователя
$stmt = $mysqli->prepare("SELECT id, username, password FROM users WHERE username = ?");
$stmt->bind_param('s', $username);
$stmt->execute();
$stmt->store_result();

if ($stmt->num_rows == 1) {
    $stmt->bind_result($id, $db_username, $db_password);
    $stmt->fetch();

    // Проверка пароля
    if (verify_password($password, $db_password)) {
        // Успешная авторизация
        $_SESSION['user_id'] = $id;
        $_SESSION['username'] = $username;
        record_login_attempt($mysqli, $ip_address, 1);  // Успешная попытка
        header('Location: welcome.php');
    } else {
        // Неверный пароль
        record_login_attempt($mysqli, $ip_address, 0);  // Неудачная попытка
        echo "Invalid username or password.";
    }
} else {
    echo "User not found.";
}

$stmt->close();
?>
