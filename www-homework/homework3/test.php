<?php
require_once "students.php";
require_once "student.php";

$students = array(
  array('fn' => '61556', 'first_name' => 'Ivan','last_name' => 'Ivanov', 
          'major' => 'Computer science', 'grade' => 5.45),
  array('fn' => '63546', 'first_name' => 'Peter', 'last_name' => 'Petrov',
              'major' => 'Computer science', 'grade' => 3.45),
  array('fn' => '61556', 'first_name' => 'Ivan', 'last_name' => 'Ivanov',
              'major' => 'Computer science', 'grade' => 4.80),
  array('fn' => '62785', 'first_name' => 'Svetlin', 'last_name' => 'Georgiev',
              'major' => 'Computer science', 'grade' => 3.76),
  array('fn' => '61845', 'first_name' => 'Stanislav', 'last_name' => 'Dimitrov',
              'major' => 'Computer science', 'grade' => 4.91),
  array('fn' => '61756', 'first_name' => 'Nedyalko', 'last_name' => 'Petrov',
              'major' => 'Computer science', 'grade' => 5.49),
  array('fn' => '61948', 'first_name' => 'Denis', 'last_name' => 'Stoyanov',
              'major' => 'Computer science', 'grade' => 5.80),
  array('fn' => '61550', 'first_name' => 'Todor', 'last_name' => 'Draganov',
              'major' => 'Computer science', 'grade' => 4.40),
  array('fn' => '61094', 'first_name' => 'Valeri', 'last_name' => 'Hristov',
              'major' => 'Computer science', 'grade' => 4.10),
  array('fn' => '61567', 'first_name' => 'Petar', 'last_name' => 'Aleksiev',
              'major' => 'Computer science', 'grade' => 4.15),
  array('fn' => '61593', 'first_name' => 'Iliyan', 'last_name' => 'Marinov',
              'major' => 'Computer science', 'grade' => 5.22)
);

// $sts = new Students();
// foreach ($students as $key => $value) {
//   $sts->insertNewStudent($value);  
// }

var_dump(Students::getStudentsData());

//var_dump(Students::findAll(4, 'first_name'));

//$st = new Student(40, 1300, "Stoyan", "Ivanov", "cs", 4);
// var_dump($st->update());
// var_dump($st);

// var_dump(Student::exists(11));
//$st->save();
//$st->delete();
?>