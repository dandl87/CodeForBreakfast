<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="com.protom.codeforbreakfast.service.ServiceAllPosts"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.Post"%>
    <!DOCTYPE >
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Code for Breakfast | Articles Refactoring</title>
    <!-- Favicon -->
    <link rel="icon" type="image/png" sizes="32x32" href="assets/images/favicon2.png">
    <!-- Remix icons -->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <!-- Swiper.js styles -->
    <link rel="stylesheet" href="assets/css/swiper-bundle.min.css"/>
    <!-- Custom styles -->
    <link rel="stylesheet" href="assets/css/articlesRefactoring.css">
</head>

<body>
    <!-- Header -->
    <header class="header" id="header">

        <nav class="navbar container">
            <a href="index.jsp">
                <h2 class="logo">Code for Breakfast</h2>
            </a>

            <div class="menu" id="menu">
                <ul class="list">
                    <li class="list-item">
                        <a href="index.jsp" class="list-link">Home</a>
                    </li>
                    <li class="list-item">
                        <a href="conferences.jsp" class="list-link">Conferences</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link current">Articles</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">News</a>
                    </li>
                    <li class="list-item">
                        <a href="membership.jsp" class="list-link">Membership</a>
                    </li>
                    <li class="list-item">
                        <a href="contacts.jsp" class="list-link">Contact</a>
                    </li>
                    
                    <li class="list-item screen-lg-hidden">
                        <a href="signup.html" class="list-link">Sign up</a>
                    </li>
                </ul>
            </div>

            <div class="list list-right">
                <button class="btn place-items-center" id="theme-toggle-btn">
                    <i class="ri-sun-line sun-icon"></i>
                    <i class="ri-moon-line moon-icon"></i>
                </button>

                <!-- Search Icon-->
                <button class="btn place-items-center" id="search-icon">
                    <i class="ri-search-line"></i>
                </button>

                <button class="btn place-items-center screen-lg-hidden menu-toggle-icon" id="menu-toggle-icon">
                    <i class="ri-menu-3-line open-menu-icon"></i>
                    <i class="ri-close-line close-menu-icon"></i>
                </button>

                <!-- LogIn Icon -->
                <button class="btn " id="login-icon">
                    <i class="ri-login-circle-fill"></i>
                </button>

                <!-- Log Out visible only when you are logged -->
                
                <button class="btn" id="logout-button">
                    <i class="ri-logout-circle-r-line"></i>
                </button>

                <a href="signUp.html" class="btn sign-up-btn fancy-border screen-sm-hidden">
                    <span>Sign up</span>
                </a>
            </div>

        </nav>

    </header>

    <!-- LogIn -->
    <div class="login-form-container container" id="login-form-container">

        <div class="form-container-inner">

           <form action="http://localhost:8086/CodeForBreakfast/login" class="form" method="post">
           <input class="form-input" type="text" placeholder="username" name="username">
           <input class="form-input" type="text" placeholder="password" name="password">
             <button class="btn form-btn" type="submit">
               <i class="ri-login-circle-fill"></i>
            </button>
           </form>
            <span class="form-note">Or press ESC to close.</span>

       </div>

       <button class="btn form-close-btn place-items-center" id="form-close-btn-login">
           <i class="ri-close-line"></i>
       </button>

   </div>

    <!-- Search -->
    <div class="search-form-container container" id="search-form-container">

        <div class="form-container-inner">

            <form action="" class="form">
                <input class="form-input" type="text" placeholder="What are you looking for?">
                <button class="btn form-btn" type="submit">
                    <i class="ri-search-line"></i>
                </button>
            </form>
            <span class="form-note">Or press ESC to close.</span>

        </div>

        <button class="btn form-close-btn place-items-center" id="form-close-btn">
            <i class="ri-close-line"></i>
        </button>

    </div>

    <!-- Console articles -->
    <section class="featured-articles section section-header-offset">

        <div class="featured-articles-container container d-grid">

            <div class="featured-content d-grid">
				
				 <!--  console  -->
                <div class="headline-banner">
                    <h3 class="headline fancy-border">
                    
                    <!--  console UserArea -->
                    	 <c:choose>
							<c:when test="${user.username!=null}"> <span class="place-items-center"> <c:out value="${user.username }"/></span>
							</c:when>  
							<c:otherwise> <span class="place-items-center"> CodeForBreakfast</span>
							</c:otherwise>
						</c:choose>
                     
                    </h3>
                    <!--  console Msg Area  -->
                    <c:choose>
							<c:when test="${user.username!=null}"> <span class="headline-description">Welcome back <c:out value="${user.name }"/></span>
							</c:when> 
							<c:when test="${errorMsg!=null}"> <span class="headline-description"><c:out value="${errorMsg }"/></span>
							</c:when>
							<c:otherwise> <span class="headline-description"> Welcome to a world made of code & coffee</span>
							</c:otherwise>
						</c:choose>
                    <!--  <span class="headline-description">My articles</span> -->
                </div>
                
                <!-- Codice Della Personal Desk sezione Post www -->
            
                <%
                ServiceAllPosts service = new ServiceAllPosts();
                ArrayList<Post> allPosts = service.caricaAllPosts();
                System.out.println(allPosts.size());
                request.setAttribute("posts",allPosts);
				%>

                


                         <c:set var="count" scope="session" value="${0}"/>
                             <c:forEach var="post" items="${posts}"> 
                                  <c:set var="count" scope="session" value="${count+1}"/>
                 	
                                    <!-- Articles --> 
                                        <div class="slide-container">
                                            <div class="slide-content">
                                                <div class="article-wrapper"></div>
                                                    <div class="articleNew">
                                                        <div class="imageNew">

                                                            <span class="overlayNew"></span>
                                                            <div class="articleNew-image">

                                                                <img src="#" alt="" class="articleNew-img">
                                                            </div>

                                                            <div class="articleNew-content">
                                                                <h2 class="content-name">Articolo 1</h2>
                                                                <p class="content-description">Questo articolo parla di questo e quello</p>

                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <a  href="${post.link}" class="article featured-article">
                                        <img src="${post.linkImg}" alt="" class="article-image">
                                            <span class="article-category">${post.category}</span> 

                                            <div class="article-data-container">
        
                                                    <div class="article-data">
                                                    <span>${post.data}</span>
                                                    <span class="article-data-spacer"></span> 
                                                    </div>
        
                                                    <h3 class="title article-title">${post.title}</h3> 
                                            </div>
                                        </a>

                                        <!-- info -->
                                        <a class="article info-article"> 
                                            

                                        <div class="info-article-data-container">

                                            <div class="article-data">
                                                <span class="article-category">${post.category}</span>
                                                <span>${post.data}</span>
                                                    
                                            </div>

                                            <h3 class="title article-title">${post.title}</h3>
                                            <h3 class="article-info">Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                                                Proin nec sagittis odio. Integer eu ante molestie, condimentum purus volutpat, pharetra mauris. 
                                                Morbi tortor velit, luctus vel pretium non, lacinia quis lorem. Donec luctus velit quis mi sodales, sit amet ultrices tellus venenatis. 
                                                Suspendisse ut urna risus. Curabitur eu magna vel elit volutpat suscipit. Ut consequat velit metus, quis gravida ante semper a. 
                                                Donec euismod mauris sed diam feugiat, blandit consequat lorem imperdiet. 
                                                Phasellus lorem est, malesuada vel sapien consequat, ullamcorper rutrum eros.</h3>

                                            </div>
                                    </a>

                                </c:forEach>
					 
                     



            </div>
            
            <!-- you articles Nav Bar-->
            <div class="sidebar d-grid">

                <h3 class="title featured-content-title">Your Articles</h3>


                <!-- Codice Della Personal Desk sezione Post -->
                <c:set var="count" scope="session" value="${0}"/>
                <c:forEach var="post" items="${personalPostList}">
                 <c:set var="count" scope="session" value="${count+1}"/>
                 	
                 	 <!-- Posts -->
               			 <a href="${post.link}" class="trending-news-box">
                            <div class="trending-news-img-box">
                                <span class="trending-number place-items-center"><c:out value="${count}" /></span>
                                <img src="${post.linkImg}" alt="" class="article-image">
                            </div>
 
                 		 <div class="trending-news-data">

                            <div class="article-data">
                            <span>${post.data}</span>
                            <span class="article-data-spacer"></span> 
                            </div>

                        <h3 class="title article-title">${post.title}</h3>

                    </div>
                </a>
                </c:forEach>


   

            </div>

        </div>

    </section>



    <!-- CONFERENCES -->
    <section class="quick-read section">

        <div class="container">

            <h2 class="title section-title" data-name="latest conferences">Conferences</h2>
            <!-- Slider main container -->
            <div class="swiper">
                <!-- Additional required wrapper -->
                <div class="swiper-wrapper">
                    <!-- Slides -->
                    <a href="#" class="article swiper-slide">
                        <img src="./assets/images/quick_read/quick_read_1.jpg" alt="" class="article-image">

                        <div class="article-data-container">
                            <div class="article-data">
                                <span>23 march 2023</span>
                                <span>xxxx</span>
                                <span class="article-data-spacer"></span>
                                <span>xxxx</span>
                            </div>
                            <h3 class="title article-title">OOOOOO</h3>
                        </div>
                    </a>
                    <!-- Slides -->
                    <a href="#" class="article swiper-slide">
                        <img src="./assets/images/quick_read/quick_read_2.jpg" alt="" class="article-image">

                        <div class="article-data-container">
                            <div class="article-data">
                                <span>23 Dec 2021</span>
                                <span class="article-data-spacer"></span>
                                <span>3 Min read</span>
                            </div>
                            <h3 class="title article-title">Sample article title</h3>
                        </div>
                    </a>
                    <!-- Slides -->
                    <a href="#" class="article swiper-slide">
                        <img src="./assets/images/quick_read/quick_read_3.jpg" alt="" class="article-image">

                        <div class="article-data-container">
                            <div class="article-data">
                                <span>23 Dec 2021</span>
                                <span class="article-data-spacer"></span>
                                <span>3 Min read</span>
                            </div>
                            <h3 class="title article-title">Sample article title</h3>
                        </div>
                    </a>
                    <!-- Slides -->
                    <a href="#" class="article swiper-slide">
                        <img src="./assets/images/quick_read/quick_read_4.jpg" alt="" class="article-image">

                        <div class="article-data-container">
                            <div class="article-data">
                                <span>23 Dec 2021</span>
                                <span class="article-data-spacer"></span>
                                <span>3 Min read</span>
                            </div>
                            <h3 class="title article-title">Sample article title</h3>
                        </div>
                    </a>
                    <!-- Slides -->
                    <a href="#" class="article swiper-slide">
                        <img src="./assets/images/quick_read/quick_read_5.jpg" alt="" class="article-image">

                        <div class="article-data-container">
                            <div class="article-data">
                                <span>23 Dec 2021</span>
                                <span class="article-data-spacer"></span>
                                <span>3 Min read</span>
                            </div>
                            <h3 class="title article-title">Sample article title</h3>
                        </div>
                    </a>
                    <!-- Slides -->
                    <a href="#" class="article swiper-slide">
                        <img src="./assets/images/quick_read/quick_read_6.jpg" alt="" class="article-image">

                        <div class="article-data-container">
                            <div class="article-data">
                                <span>23 Dec 2021</span>
                                <span class="article-data-spacer"></span>
                                <span>3 Min read</span>
                            </div>
                            <h3 class="title article-title">Sample article title</h3>
                        </div>
                    </a>
                </div>
                <!-- Navigation buttons -->
                <div class="swiper-button-prev swiper-controls"></div>
                <div class="swiper-button-next swiper-controls"></div>
                <!-- Pagination -->
                <div class="swiper-pagination"></div>
            </div>

        </div>

    </section>
  

    <!-- Footer -->
    <footer class="footer section">

        <div class="footer-container container d-grid">
            
            <div class="company-data">
                <a href="./index.html">
                    <h2 class="logo">Code for Breakfast</h2>
                </a>
                <p class="company-description">Insight into web development and more</p>
                
                <ul class="list social-media">
                    <li class="list-item">
                        <a href="#" class="list-link"><i class="ri-instagram-line"></i></a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link"><i class="ri-facebook-circle-line"></i></a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link"><i class="ri-twitter-line"></i></a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link"><i class="ri-pinterest-line"></i></a>
                    </li>
                </ul>

                <span class="copyright-notice">&copy;2023 Code for Breakfast. All rights reserved.</span>
            </div>

            <div>
                <h6 class="title footer-title">Categories</h6>
                
                <ul class="footer-list list">
                    <li class="list-item">
                        <a href="#" class="list-link">Travel</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Food</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Technology</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Health</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Nature</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Fitness</a>
                    </li>
                </ul>

            </div>

            <div>
                <h6 class="title footer-title">Useful links</h6>
                
                <ul class="footer-list list">
                    <li class="list-item">
                        <a href="#" class="list-link">Home</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Elements</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Tags</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Authors</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Membership</a>
                    </li>
                </ul>

            </div>

            <div>
                <h6 class="title footer-title">Company</h6>
                
                <ul class="footer-list list">
                    <li class="list-item">
                        <a href="#" class="list-link">Contact us</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">F.A.Q</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Careers</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Authors</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link">Memberships</a>
                    </li>
                </ul>

            </div>

        </div>
        
    </footer>

    <!-- Swiper.js -->
    <script src="./assets/js/swiper-bundle.min.js"></script>
    <!-- Custom script -->
    <script src="./assets/js/main.js"></script>
</body>
</html>