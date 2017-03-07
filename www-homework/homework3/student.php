<?php

require_once "connection.php";

class Student {
     private $id;
     private $fn;
     private $first_name;
     private $last_name;
     private $major;
     private $grade;
     private $last_updated;

     public function __construct($id, $fn, $first_name, $last_name, $major, $grade) {
        $this->id = $id;
        $this->fn = $fn;
        $this->first_name = $first_name;
        $this->last_name = $last_name;
        $this->major = $major;
        $this->grade = $grade;
     }

     public function __get($property) {
        if (property_exists($this, $property)) {
            return $this->$property;
        }
     }

     public function __set($property, $value) {
        if (property_exists($this, $property)) {
            $this->$property = $value;
        }
     }

     public static function getConnection() {
        return Connection::getInstance()->handler;
     }

     public function create() {
        self::getConnection()->beginTransaction();

        $sql = "INSERT INTO students(fn, first_name, last_name, major, grade, last_updated)
                VALUES(:fn, :first_name, :last_name, :major, :grade, :last_updated)";

        $stmt = self::getConnection()->prepare($sql);

        $this->last_updated = date("Y-m-d");
        $stmt->bindParam(':fn', $this->fn, PDO::PARAM_INT);
        $stmt->bindParam(':first_name', $this->first_name);
        $stmt->bindParam(':last_name', $this->last_name);
        $stmt->bindParam(':major', $this->major);
        $stmt->bindParam(':grade', $this->grade);
        $stmt->bindParam(':last_updated', $this->last_updated);

        $result = $stmt->execute();

        if($result) {
            $this->id = self::getConnection()->lastInsertId();
            self::getConnection()->commit();
        } else {
            self::getConnection()->rollBack();
        }
        return $result;
     }

     public function update() {
        self::getConnection()->beginTransaction();

        $sql = "UPDATE students 
                SET first_name = :first_name, last_name = :last_name,
                major = :major, grade = :grade, last_updated = :last_updated
                WHERE fn = :fn";

        $stmt = self::getConnection()->prepare($sql);

        $this->last_updated = date("Y-m-d");
        $stmt->bindParam(':fn', $this->fn, PDO::PARAM_INT);
        $stmt->bindParam(':first_name', $this->first_name);
        $stmt->bindParam(':last_name', $this->last_name);
        $stmt->bindParam(':major', $this->major);
        $stmt->bindParam(':grade', $this->grade);
        $stmt->bindParam(':last_updated', $this->last_updated);

        $result = $stmt->execute();
        if($result) {
            self::getConnection()->commit();
        } else {
            self::getConnection()->rollBack();
        }
        return $result;
     }

     public function save() {
        if (Student::exists($this->fn)) {
            $this->update();
        } else {
            $this->create();
        }
     }

     public function delete() {
        self::getConnection()->beginTransaction();

        $sql = "DELETE FROM students
                WHERE fn = :fn";

        $stmt = self::getConnection()->prepare($sql);
        $stmt->bindParam(':fn', $this->fn, PDO::PARAM_INT);

        $result = $stmt->execute();
        if($result) {
            self::getConnection()->commit();
        } else {
            self::getConnection()->rollBack();
        }
     }

     public static function find($fn) {
        $sql = "SELECT * FROM students
                WHERE fn = :fn";
        $stmt = self::getConnection()->prepare($sql);
        $stmt->bindParam(':fn', $fn);
        $stmt->setFetchMode(PDO::FETCH_ASSOC);
        $stmt->execute(); 
        $row = $stmt->fetch();
        $new_id = $row["id"];
        $new_fn = $row["fn"];
        $new_first_name = $row["first_name"];
        $new_last_name = $row["last_name"];
        $new_major = $row["major"];
        $new_grade = $row["grade"];

        $student = new Student($new_id, $new_fn, $new_first_name, $new_last_name, $new_major, $new_grade);
        return $student;
     }

     public static function exists($fn) {
        $sql = "SELECT COUNT(*) as students_count
                FROM students
                WHERE fn = ?";
        $stmt = self::getConnection()->prepare($sql);
        $stmt->execute(array($fn)); 
        return $stmt->fetchColumn() > 0;
     }

     public static function count() {
        $sql = "SELECT COUNT(*)
                FROM students";
        $stmt = self::getConnection()->prepare($sql);
        $stmt->execute(); 
        return $stmt->fetchColumn();
     }

}
?>