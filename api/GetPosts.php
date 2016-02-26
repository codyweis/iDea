<?php

	if($_SERVER['REQUEST_METHOD'] == 'GET'){
	
		require_once('connect.inc.php');
		
		$sql = "SELECT * FROM IDEA";
		$run = mysqli_query($conn, $sql);
		
		$result = array();
		
		while($row = mysqli_fetch_array($run)){
			array_push($result, array(
				'id' => $row['id'],
				'user_id' => $row['user_id'],
				'topic_id' => $row['topic_id'],
				'content' => $row['content'],
				'post_date' => $row['post_date']
			));
		}
		
		echo json_encode(array('result'=>$result));
		
		mysqli_close($conn);
	}
?>