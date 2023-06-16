<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="com.protom.codeforbreakfast.service.ServiceAllUsers"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.User"%>
    <%@ page import="com.protom.codeforbreakfast.dbconnections.DbConnectionMySql"%>
    
     <%   if(session.getAttribute("user")==null)
   			 response.sendRedirect("/CodeForBreakfast"); 
	 %>
	 
	 
    <!DOCTYPE >
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Code for Breakfast | Membership </title>
    <!-- Favicon -->
    <link rel="icon" type="image/png" sizes="32x32" href="assets/images/favicon2.png">
    <!-- Remix icons -->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <!-- Swiper.js styles -->
    <link rel="stylesheet" href="assets/css/swiper-bundle.min.css"/>
    <!-- Custom styles -->
    <link rel="stylesheet" href="assets/css/membership.css">
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
                        <a href="/CodeForBreakfast/home" class="list-link">Home</a>
                    </li>
                    <li class="list-item">
		               <a href="/CodeForBreakfast/conferences?page=1" class="list-link">Conferences</a>
		            </li>
		                    
		            <li class="list-item">
		               <a href="/CodeForBreakfast/articles?page=1" class="list-link">Articles</a>
		            </li>
                    
                    <li class="list-item">
                        <a href="#" class="list-link">News</a>
                    </li>
                    <li class="list-item">
                        <a href="#" class="list-link current">Membership</a>
                    </li>
                    <li class="list-item">
                        <a href="/CodeForBreakfast/contact" class="list-link">Contact</a>
                    </li>
                    
                    <li class="list-item screen-lg-hidden">
                        <a href="signup.html" class="list-link">Sign up</a>
                    </li>
                </ul>
            </div>

            <div class="list list-right">
                 

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

                <!-- Log Out Icon -->
                    <a href="http://192.168.1.109:8086/CodeForBreakfast/logout">
                        <button class="btn" id="logout-button">
                            <i class="ri-logout-circle-r-line"></i>
                        </button>
                    </a>

                <a href="signUp.html" class="btn sign-up-btn fancy-border screen-sm-hidden">
                    <span>Sign up</span>
                </a>
            </div>

        </nav>

    </header>

    <!-- LogIn -->
    <div class="login-form-container container" id="login-form-container">

        <div class="form-container-inner">

           <form action="http://192.168.1.109:8086/CodeForBreakfast/login" class="form" method="post">
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
                    <span class="headline-description"> 
                   
							 <c:choose>
								<c:when test="${user==null}"> 
									Welcome to a world made of code & coffee
								</c:when>
								<c:otherwise>
									<c:out value="${infoMsg}"/>
								</c:otherwise>
							</c:choose>	
							</span>
							 
						
						
						
						
                    <!--  <span class="headline-description">My articles</span> -->
                </div>
                
                <!-- Codice Della Personal Desk sezione Post www -->
            
                <%
                DbConnectionMySql connection= DbConnectionMySql.getInstance();
   				connection.avviaConnessione();
                ServiceAllUsers service = new ServiceAllUsers();
                ArrayList<User> allUsers = service.caricaAllUsers();
                request.setAttribute("users",allUsers);
                connection.chiudiConnessione();
				%>
                <c:set var="count" scope="session" value="${0}"/>
                <c:forEach var="user" items="${users}"> 
                 <c:set var="count" scope="session" value="${count+1}"/>
                 	
                 	 <!-- Articles -->
                 	 
                 	 
               			 <a   class="article featured-article">
                 		    <img src="" alt="" class="article-image">
                             <span class="article-category">toDefine</span> 

                             <div class="article-data-container">
   
                               <div class="article-data">
                                 <span>${user.name}</span>
                                 <span>${user.surname}</span>
                                 <span class="article-data-spacer"></span> 
                               </div>
   
                                <h3 class="title article-title">${user.username}</h3> 
                            </div>
                        </a>

                        <!-- info -->
                        <a class="article info-article"> 
                            

                         <div class="info-article-data-container">

                               <div class="article-data">
                                <span class="article-category">toBeDefine</span>
                                   <span>toBeDefine</span>
                                    
                               </div>

                               <h3 class="title article-title">toBeDefine</h3>
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