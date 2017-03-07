<?php
require_once "student.php";
require_once "connection.php";

class Students {

public static function getConnection() {
        return Connection::getInstance()->handler;
     }

public static function getStudentsData() {
  if (Student::count() == 0) {
    die("There are no students!");
  } else {
    self::getBestStudents();
  }
}

public static function getBestStudents() {
    $sql = "SELECT *
                FROM students
                ORDER BY grade DESC
                LIMIT 10";
    $stmt = self::getConnection()->prepare($sql);
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

public static function insertNewStudent($array) {
    $student = new Student(0, $array['fn'], $array['first_name'], $array['last_name'], $array['major'], $array['grade']);
    $student->save();
}

public static function findAll($limit = 10, $orderby = 'fn') {
	$sql = "SELECT *
                FROM students
                ORDER BY $orderby ASC
                LIMIT :limit_count";
  $stmt = self::getConnection()->prepare($sql);
  $stmt->bindParam(':limit_count', $limit, PDO::PARAM_INT);
  $stmt->execute();
  $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
  return $result;
    
}

}
?>