<?php

require_once 'config.php';

class Connection {
    private static $instance = null;

    private $handler;

    private function __construct() {
        try {
            $this->handler = new PDO("mysql:host=".DB_SERVER.";dbname=".DB_NAME, DB_USERNAME, DB_PASSWORD);
        } catch (Exception $e) {
            die("Database connection failed!");
        }
        $this->handler->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }

    public static function getInstance() {
        if(!isset(self::$instance)) {
            self::$instance = new Connection();
        }
        return self::$instance;
    }

    public function __get($property) {
       if (property_exists($this, $property)) {
           return $this->$property;
       }
    }
}

?>