<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link href="style.css" rel="stylesheet" type="text/css" />
    <title>TO DO LIST</title>
  </head>
  <body>
  	<div id="add-container">
      <h1>할일 등록</h1>
      <form action="todoForm" method="post" accept-charset="utf-8">
      <fieldset>
	    <p>
	      <label for="todo-title">어떤 일인가요?</label><br>
	      <input type="text" id="todo-title" name="title" placeholder="ex) 공부하기"><br>
	    </p>
        <p>
	      <label for="todo-name">누가 할 일인가요?</label><br>
          <input type="text" id="todo-name" name="name" placeholder="ex) 홍길동"><br>
	    </p>
	    <p>
	      우선 순위를 선택하세요<br>
	      <input type="radio" id="first" class="todo-seq" name="sequence" value="1" checked>
	      <label for="first" class="seq-label">1순위</label>
	      <input type="radio" id="second" class="todo-seq" name="sequence" value="2">
	      <label for="second" class="seq-label">2순위</label>
	      <input type="radio" id="third" class="todo-seq" name="sequence" value="3">
	      <label for="third" class="seq-label">3순위</label><br>
	    </p>
	    <a href="main" id="prev-btn" class="button">&lt 이전</a>
	    <button id="reset-btn" class="add-button" type="reset">내용지우기</button>
	    <button id="submit-btn" class="add-button" type="submit">제출</button>
	   </fieldset>
	   </form> 	        
    </div>
  </body>
</html>