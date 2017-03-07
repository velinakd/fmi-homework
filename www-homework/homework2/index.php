<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" />
	<style>
	table, td {
    border: 1px solid #000;
    text-align: center;
    border-collapse: collapse;
	}
	</style>
	<title>Example</title>
</head>
<body>
	<?php
      if(isset($_GET["year"])&&(isset($_GET["month"]))) {
        $currentTime = strtotime($_GET['month'].' '.$_GET['year']);
      } else {
        $currentTime = time();
      }
      
	  $currentMonthName = date('F', $currentTime);
	  $currentYear = date('Y', $currentTime);
      $currentMonth = "$currentMonthName $currentYear";

      $oneMonthAgo = strtotime("-1 months", $currentTime);
      $oneMonthLater = strtotime("+1 months", $currentTime);
      $previousMonthName = date('F', $oneMonthAgo);
	  $nextMonthName = date('F', $oneMonthLater);
      $previousMonthYear = date('Y', $oneMonthAgo);
      $nextMonthYear = date('Y', $oneMonthLater);
	    
	  $month = array();

      $daysInMonth = date('t', $currentTime);
      for($i = 1; $i <= $daysInMonth; $i++) {
        $currentDay = strtotime("$i $currentMonth");
        $printedDate = date('j', $currentDay);
        $weekNumber = date('W', $currentDay);
        $weekDay = date('N', $currentDay);
        if (!array_key_exists($weekNumber, $month)) {
            $month[$weekNumber] = array();
        }
        $month[$weekNumber][$weekDay] = $printedDate;

      }
	?>

	<table>
  <tr>
    <td colspan="2"><a href="?month=<?= $previousMonthName ?>&year=<?=$previousMonthYear ?>"><?php echo "$previousMonthName $previousMonthYear"; ?></a></td>
    <td colspan="3"><?php echo $currentMonth; ?></td>
	<td colspan="2"><a href="?month=<?= $nextMonthName ?>&year=<?=$nextMonthYear ?>"> <?php echo "$nextMonthName $nextMonthYear"; ?></a></td>
  </tr>
  <tr>
    <td>Понеделник</td>
    <td>Вторник</td>
    <td>Сряда</td>
    <td>Четвъртък</td>
    <td>Петък</td>
    <td>Събота</td>
    <td>Неделя</td>
  </tr>
  <?php foreach ($month as $weekNumber => $week) { ?>
  <tr>
	<?php for($j = 1; $j <= 7; $j++) { ?>
    <td>
	<?php echo $week[$j]; ?>
	</td>
	<?php } ?>
	<?php } ?>
  </tr>
</table>
</body>
</html>