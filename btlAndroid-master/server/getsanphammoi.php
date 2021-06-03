<?php
	include "connectsanphammoi.php";
	$db= new Database;
	$db->connect();	
	error_reporting(0);
	$data=$db->Getinfo();

	echo json_encode($data);	

?>