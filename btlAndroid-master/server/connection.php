<?php 
	$host = "localhost";
	$user = "root";
	$pass = "";
	$dbname = "xe";

	$conn = mysqli_connect($host, $user, $pass, $dbname);
	mysqli_query($conn, "SET NAMES 'UTF8'");

	if (!$conn) {
		echo "Check database";
	}
 ?>