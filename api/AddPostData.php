<?php
session_start();
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		require_once('connect.inc.php');

                $user= $_POST['user'];
		$post_id= $_POST['post_id'];               
		
		$query= "INSERT INTO LIKED(user, post_id) VALUES('$user', '$post_id')";
		
		if(mysqli_query($conn, $query)){
			echo "success";
		}else{
			echo "errorone";
		}
	}else{
		echo 'error';
	}	

		mysqli_close($conn);

?>