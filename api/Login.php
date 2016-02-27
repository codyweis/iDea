<?php
session_start();
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		$username = $_POST["username"];
		$password = $_POST["password"];
		
		$sql = "SELECT * FROM USER WHERE username = '$username'";
		
		require_once('connect.inc.php');
		
		$result = mysqli_query($conn, $sql);
		$row = mysqli_fetch_array($result);
		$verify = password_verify($password, $row[4]);
		
		if($verify){
			$_SESSION["username"] = $username;
			echo 'connected';
			echo session_id();
		}else{
			echo 'check details';
		}

		mysqli_close($conn);
	}
?>