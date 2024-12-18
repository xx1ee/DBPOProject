<?php
// functions.php

// Функция для записи попытки входа
function record_login_attempt($mysqli, $ip, $success) {
    $stmt = $mysqli->prepare("INSERT INTO login_attempts (ip_address, success) VALUES (?, ?)");
    $stmt->bind_param('si', $ip, $success);
    $stmt->execute();
    $stmt->close();
}

// Функция для проверки, заблокирован ли IP
function is_ip_blocked($mysqli, $ip, $max_attempts = 5, $block_time = 15) {
    $attempts = 0; // Инициализация переменной attempts

    $stmt = $mysqli->prepare("
        SELECT COUNT(*) AS attempts 
        FROM login_attempts 
        WHERE ip_address = ? 
        AND success = 0 
        AND attempt_time > NOW() - INTERVAL ? MINUTE
    ");
    $stmt->bind_param('si', $ip, $block_time);
    $stmt->execute();
    $stmt->bind_result($attempts);
    $stmt->fetch();
    $stmt->close();

    return $attempts >= $max_attempts;
}

// Функция для хеширования пароля с солью
function hash_password($password) {
    return password_hash($password, PASSWORD_BCRYPT);
}

// Функция для проверки пароля
function verify_password($input_password, $stored_hash) {
    return password_verify($input_password, $stored_hash);
}
?>
