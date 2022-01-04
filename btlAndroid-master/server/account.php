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
			echo "User đã tồn tại vui lòng kiểm tra lại";
	}else{
		$query = "INSERT INTO account(id,user,password) VALUES(null,'$user','$password')";
			if(mysqli_query($conn, $query)){
				echo "Đăng kí thành công";
			}else{
				echo"Đăng kí thất bại";
			}
		}
	}else{
			echo "Bạn hãy kiểm tra lại trường dữ liệu";
	}
?>