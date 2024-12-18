<?php
// db_config.php
$servername = "localhost";
$username = "root";  // Замените на ваше имя пользователя
$password = "";  // Замените на ваш пароль
$dbname = "auth_system";  // Замените на имя вашей базы данных

// Создание подключения
$mysqli = new mysqli($servername, $username, $password, $dbname);

// Проверка подключения
if ($mysqli->connect_error) {
    die("Connection failed: " . $mysqli->connect_error);
}
?>
