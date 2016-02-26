<?php

	if($_SERVER['REQUEST_METHOD'] == 'GET'){
		session_start();
		$sessionid = session_id();
		echo json_encode(['sessionId' => $sessionid]);
	}
	
?>	