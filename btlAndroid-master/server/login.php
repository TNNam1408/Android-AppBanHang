<?php
	include"connection.php";
	$user = $_POST['user'];
	$password = $_POST['password'];

		// $user = '123';
		// $password = '123';

	if(strlen($user)> 0 && strlen($password) >0 ){

		$checkuser = "SELECT * FROM account WHERE USER = '$user' AND PASSWORD = '$password' ";
		$result = mysqli_query($conn,$checkuser);
		if(mysqli_num_rows($result) > 0){
				echo "Dang nhap thanh cong";
			}else{
				echo"Dang nhap that bai";
			}
	}else{
		echo "Ban hay kiem tra lai du lieu";
	}
?>
