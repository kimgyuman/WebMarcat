<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Marcat</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">


  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Customized Bootstrap Stylesheet -->
  <link href="../../../../resources/css/manager/bootstrap.min.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="../../../../resources/css/manager/style.css" rel="stylesheet">

</head>

<body>
<div class="container-fluid position-relative bg-white d-flex p-0">
  <!-- Spinner Start -->
  <div id="spinner"
       class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
  <!-- Spinner End -->


  <!-- Sidebar Start -->
  <div class="sidebar pe-4 pb-3">
    <nav class="navbar">
      <a href="/admin" class="navbar-brand mx-4 mb-3">
        <h3 class="text-primary">&nbsp;&nbsp;<i class="fa fa-hashtag me-2"></i>ADMIN</h3>
      </a>

      <div class="navbar-nav w-100">
        <a href="/admin" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>대시보드</a>
        <a href="/admin/memberManagement" class="nav-item nav-link"><i class="fa-solid fa-address-book me-2"></i>회원관리</a>
        <a href="/admin/managerChart" class="nav-item nav-link"><i class="fa-solid fa-chart-column me-2"></i>차트</a>
        <a href="/admin/notifyHistory" class="nav-item nav-link"><i class="fa-solid fa-list-ul me-2"></i>상품 게시글 신고내역</a>
        <a href="/admin/boardNotifyHistory" class="nav-item nav-link"><i class="fa-solid fa-list-ul me-2"></i>게시글 신고내역</a>
        <a href="/admin/goodsList" class="nav-item nav-link"><i class="fa-solid fa-border-all me-2"></i>전체 상품 게시글</a>
        <a href="/admin/boardList" class="nav-item nav-link"><i class="fa-solid fa-border-all me-2"></i>전체 게시글</a>
        <a href="/admin/adManagement" class="nav-item nav-link active"><i class="fa-solid fa-rectangle-ad me-2"></i>광고 관리</a>
      </div>
    </nav>
  </div>
  <!-- Sidebar End -->


  <!-- Content Start -->
  <div class="content bg-light">

    <!-- Navbar Start -->
    <div class="nav-header">
      <nav class="navbar navbar-expand navbar-light sticky-top px-4 py-0">
        <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
          <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
        </a>
        <a href="#" class="sidebar-toggler flex-shrink-0">
          <i class="fa fa-bars"></i>
        </a>


        <div class="navbar-nav align-items-center ms-auto">
          <div class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
              <i class="fa-solid fa-user-large"></i>
              <span class="d-none d-lg-inline-flex">admin</span>
            </a>
            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
              <a href="/" class="dropdown-item">홈으로</a>
              <a href="/logout" class="dropdown-item">로그아웃</a>
            </div>
          </div>
        </div>
      </nav>
    </div>
    <!-- Navbar End -->


    <!-- Recent Sales Start -->
    <input type="hidden" id="expiryDate" value="${ad.expiryTime}">
    <input type="hidden" id="cId" value="${ad.categoryId}">
    <div class="container-fluid pt-4 px-4">
      <div class="row g-4">
        <div class="col-sm-12 col-xl-12 bg-white">
          <div id="all_file_container">
            <h2>광고 수정</h2>
            <form:form modelAttribute="addAdDTO" action="/admin/adUpdate" method="post" enctype="multipart/form-data"
                       onsubmit="return adValidation();">
            <hr>
            <div class="d-flex justify-content-center">
              <img id="preview-image" src="${ad.savedFileName}">
            </div>
              <div class="originFileName" type="text">원래 파일명 : ${ad.originFileName}</div>
            <input id="input-image" class="adFile mt-3" type="file" name="file">
          </div>
          <input type="hidden" id="beforeOrigin" name="beforeOrigin" value="${ad.originFileName}">
          <input type="hidden" id="beforeSaved" name="beforeSaved" value="${ad.savedFileName}">
          <input type="hidden" id="adId" name="adId" value="${ad.id}">


          <div class="row justify-content-center">
            <div class="col-lg-8 col-xl-7">

              <div class="form-floating mb-4">
                <form:select class="form-control" name="cateId" id="cateId" path="cateId" >
                  <form:option value="100" label="중고차" />
                  <form:option value="200" label="디지털기기" />
                  <form:option value="300" label="생활가전" />
                  <form:option value="400" label="가구,인테리어" />
                  <form:option value="500" label="유아동" />
                  <form:option value="600" label="생활,가공식품" />
                  <form:option value="700" label="유아도서" />
                  <form:option value="800" label="스포츠,레저" />
                  <form:option value="900" label="여성잡화" />
                  <form:option value="1000" label="여성의류" />
                  <form:option value="1100" label="남성패션,잡화" />
                  <form:option value="1200" label="게임,취미" />
                  <form:option value="1300" label="뷰티,미용" />
                  <form:option value="1400" label="반려동물용품" />
                  <form:option value="1500" label="도서/티켓/음반" />
                  <form:option value="1600" label="식물" />
                  <form:option value="1700" label="기타중고" />
                </form:select>

                <form:errors path="cateId"></form:errors>
                <label for="cateId">카테고리</label>
              </div>

              <div class="form-floating mb-4">
                <form:input class="form-control" name="expiryTime" id="expiryTime" type="date" path="expiryTime" ></form:input>
                <form:errors path="expiryTime"></form:errors>
                <label for="expiryTime">종료시기</label>
              </div>

              <div class="form-floating mb-4">
                <form:input class="form-control" name="title" id="title" type="text" path="title" value="${ad.title}"></form:input>
                <form:errors path="title"></form:errors>
                <label for="title">제목</label>
              </div>

              <div class="form-floating mb-5">
                <form:input class="form-control" name="url" id="url" type="text" path="url" value="${ad.url}"></form:input>
                <form:errors path="url"></form:errors>
                <label for="url">바로가기 url</label>
              </div>

            </div>
          </div>
          <div class="ad-btn mb-3 mt-3">
            <button id="AD_submit" type="submit">등록</button>
            </form:form>
            <button onclick="location.href='/admin/adManagement'">취소</button>
          </div>
        </div>
      </div>
    </div>


    <!-- Footer Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="bg-light rounded-top p-4">
        <div class="row">
          <div class="col-12 col-sm-6 text-center text-sm-start">
            &copy; <a href="/">Marcat</a>, All Right Reserved.
          </div>
        </div>
      </div>
    </div>
    <!-- Footer End -->
  </div>
  <!-- Content End -->


</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>

<!-- Template Javascript -->
<script src="../../../../resources/js/manager/managerMain.js"></script>
<script src="../../../../resources/js/manager/updateAd.js"></script>

</body>

</html>