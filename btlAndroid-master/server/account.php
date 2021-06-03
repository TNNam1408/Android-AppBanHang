<?php
	include"connection.php";
	$user = $_POST['user'];
	$password = $_POST['password'];

	// $user = 'khach5';
	// $password = 'khach5';
	if(strlen($user)> 0 && strlen($password) >0 ){

		$checkuser = "SELECT *FROM account WHERE USER='$user'";
		$result = mysqli_query($conn,$checkuser);
		if(mysqli_num_rows($result) > 0){
			echo "User Da Ton Tai !!";
	}else{
		$query = "INSERT INTO account(id,user,password) VALUES(null,'$user','$password')";
			if(mysqli_query($conn, $query)){
				echo "Dang ky thanh cong";
			}else{
				echo"That bai";
			}
		}
	}else{
			echo "Ban hay kiem tra lai du lieu";
	}
?>