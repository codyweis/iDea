
<?php

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		require_once 'connect.inc.php';
		
		$fname = $_POST['fname'];
		$lname = $_POST['lname'];
		$username = $_POST['username'];
		$password = password_hash($_POST["password"], PASSWORD_DEFAULT, ['cost' => 10]);
		$email = $_POST['email'];
		
		$get_username = "SELECT * FROM USER WHERE username = '$username'";
		$run_username = mysqli_query($conn, $get_username);
		$check_username = mysqli_num_rows($run_username);
		
		$get_email = "SELECT * FROM USER WHERE email = '$email'";
		$run_email = mysqli_query($conn, $get_email);
		$check_email = mysqli_num_rows($run_email);
		
		if($check_username == 1){
			echo "Username already registered";
			exit();
		}
		if($check_email == 1){
			echo "Email already registered";
			exit();
		}

		$sql = "INSERT INTO USER (fname, lname, username, password, email) VALUES ('$fname', '$lname', '$username', '$password', '$email')";
		if(mysqli_query($conn, $sql)){
			echo "successful";
		}else{
			echo "Could not register";
		}
	}else{
		echo 'error';
	}	
		mysqli_close($conn);
?>
