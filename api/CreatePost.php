<?php
session_start();
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		require_once('connect.inc.php');
		
		$user_id = $_SESSION['username'];
		$content = $_POST['content'];
		$topic_id = $_POST['topic'];
		
		$sql = "INSERT INTO IDEA(user_id, topic_id, content, post_date) VALUES ('$user_id', '$topic_id', '$content', NOW())";
		
		if(mysqli_query($conn, $sql)){
			echo "created";
		}else{
			echo "Could not create post";
		}
	}else{
		echo 'error';
	}	

		mysqli_close($conn);

?>
