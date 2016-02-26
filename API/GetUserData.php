<?php
session_start();
	if($_SERVER['REQUEST_METHOD'] == 'GET'){
		
		$username = $_SESSION['username'];
		
		$sql = "SELECT * FROM USER WHERE username = '$username'";
		
		require_once('connect.inc.php');
		
		$run = mysqli_query($conn, $sql);
		$result = array();
		
		while($row = mysqli_fetch_array($run)){
			array_push($result, array(
				'id' => $row['id'],
				'fname' => $row['fname'],
				'lname' => $row['lname'],
				'username' => $row['username'],
				'email' => $row['email'],
			));
		}
		
		echo json_encode(array('result'=>$result));

		mysqli_close($conn);
	}
?>