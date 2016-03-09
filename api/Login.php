<?php
session_start();
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
		require_once('connect.inc.php');
		 
		$sql = "SELECT username, password FROM USER WHERE username = ?";
		
		$stmt = $conn->prepare($sql);

		$username = $_POST["username"];
		$password = $_POST["password"];

		$stmt->bind_param("s", $username);

		$stmt->execute();
		
		$stmt->bind_result($user, $pass);

		while($stmt->fetch()){
			$verify = password_verify($password, $pass);
		}

		if($verify){
			$_SESSION["username"] = $username;
			echo 'connected';
		}else{
			echo 'check details';
		}

		mysqli_close($conn);
	}
?>
