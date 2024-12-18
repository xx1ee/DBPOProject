<?php
// welcome.php

session_start();

// Проверка авторизации
if (!isset($_SESSION['user_id'])) {
    header('Location: login.php');
    exit;
}

echo "Welcome, " . $_SESSION['username'] . "!";
?>
