<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="com.protom.codeforbreakfast.service.ServiceAllPosts"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.Post"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.User"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.SottoscrizionePost"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.Msg"%>
    <!DOCTYPE >
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Code for Breakfast | Articles </title>
    <!-- Favicon -->
    <link rel="icon" type="image/png" sizes="32x32" href="assets/images/favicon2.png">
    <!-- Remix icons -->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <!-- Swiper.js styles -->
    <link rel="stylesheet" href="assets/css/swiper-bundle.min.css"/>
    <!-- Custom styles -->
    <link rel="stylesheet" href="assets/css/articles.css">
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
							<c:when test="${user.username!=null && infoMsg==null}"> <span class="headline-description">Welcome back <c:out value="${user.name }"/></span>
							</c:when> 
							<c:when test="${infoMsg!=null}"> <span class="headline-description"><c:out value="${infoMsg.getMessage() }"/></span>
							</c:when>
							<c:otherwise> <span class="headline-description"> Welcome to a world made of code & coffee</span>
							</c:otherwise>
						</c:choose>
                    <!--  <span class="headline-description">My articles</span> -->
                </div>
                
                <!-- Codice Della Personal Desk sezione Post  -->
            
                <%
                int pageNumber = 2;
                ServiceAllPosts service = new ServiceAllPosts(); 
                ArrayList<Post> allPosts = service.caricaAllPostsOfPage(pageNumber);
                System.out.println(allPosts.size());
                request.setAttribute("posts",allPosts);
				%>

                
                       

                         <c:set var="count" scope="session" value="${0}"/>
                             <c:forEach var="post" items="${posts}"> 
                                  <c:set var="count" scope="session" value="${count+1}"/>
                 				 
                                    <!-- Articles --> 

                                        <div  href="${post.link}" class="article featured-article">
                                        <img src="${post.linkImg}" alt="" class="article-image">
                                            <span class="article-category">${post.category}</span> 

                                            <div class="article-data-container">
        
                                                    <div class="article-data">
                                                    
                                                        <!-- add or remove function-->
                                                        <c:set var="found" value="${false}" />
                                                        <c:forEach var="sottoscrizione" items="${user.getSottoscrizioniPost()}"> 
                                                        	 
	                                                        <c:if test="${post.getId().equals(sottoscrizione.getPost().getId())==true}">
	                                                        	<c:set var="found" value="${true}" /> 
	                                                        </c:if> 
	                                                     </c:forEach>
	                                                     
	                                                      
	                                                        <!-- Add -->
	                                                        <c:choose>
	                                                        <c:when test="${found==false}"> 
	                                                         	 <c:set var="titleURL">
	                                                        	 <c:url value="http://localhost:8086/CodeForBreakfast/addPost" >
	                                                       		 	<c:param name="postId" value="${post.getId()}"/>
	                                                       		 	<c:param name="articlesPage" value="${2}"/> 
	                                                         	 </c:url>
	                                                         </c:set>
	                                                         
	                                                         <a href="${titleURL}" > 
	                                                        <button class="btn" id="add-button">
	                                                            <i class="ri-add-box-line"></i>
	                                                        </button>
	                                                       
	                                                        </a>
															</c:when>
															<c:otherwise> 
																<c:set var="titleURL">
		                                                        	 <c:url value="http://localhost:8086/CodeForBreakfast/removePostFromArticle" >
		                                                       		 	<c:param name="postId" value="${post.getId()}"/>
		                                                       		 	<c:param name="articlesPage" value="${2}"/> 
		                                                         	 </c:url>
		                                                         </c:set>
		                                                         
		                                                         <a href="${titleURL}" > 
		                                                        <button class="btn" id="add-button">
		                                                            <i class="ri-delete-bin-line"></i>
		                                                        </button>
		                                                       
		                                                        </a>
															</c:otherwise>
															</c:choose>
	                                                        
                                                        <!-- end add function-->
                                                        
                                                        
							            
							            

                                                        <span>${post.data}</span> 
                                                        </div>
        
                                                    <h3 class="title article-title">${post.title}</h3> 
                                            </div>
                                        </div>

                                        <!-- info -->
                                        <a href="http://ww.google.com" class="article info-article"> 
                                            

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


                
   
                <c:forEach var="sottoscrizione" items="${user.getSottoscrizioniPost()}"> 
                 	
                 	 <!-- Posts -->
               			 <div href="${sottoscrizione.getPost().getLink()}" class="trending-news-box">
                            <div class="trending-news-img-box">
                                <span class="trending-number place-items-center"><c:out value="${sottoscrizione.getPosition()}" /></span>
                               <a href="http://www.google.com""> <img src="${sottoscrizione.getPost().getLinkImg()}" alt="" class="article-image"> </a>
                            </div>
 
                 		 <div class="trending-news-data">

                            <div class="article-data"> 

                             
                           <!--  remove Article -->
                            
                            <c:set var="titleURL">
		                    	<c:url value="http://localhost:8086/CodeForBreakfast/removePostFromArticle" >
		                    		<c:param name="postId" value="${sottoscrizione.getPost().getId()}"/>
		                    		<c:param name="articlesPage" value="${1}"/> 
		                    	</c:url>
		 					</c:set>		                                                         
							<a href="${titleURL}" > 
							<button class="btn" id="add-button">
								<i class="ri-delete-bin-line"></i>
							</button> 
							</a>
							<span class="article-data-spacer"></span> 
							
                              <!-- arrow up function-->
                              	  <c:if test="${sottoscrizione.getPosition()!=1}">
                            	    <c:set var="titleURL2">
				                    		<c:url value="http://localhost:8086/CodeForBreakfast/moveUpPost" >
				                    			<c:param name="SottoscrizioneId" value="${sottoscrizione.getId()}"/>
				                    			  <c:param name="articlesPage" value="${2}"/>   
				                    		</c:url>
		 								</c:set>   	 
									<a href="${titleURL2}" >                                                   
									<button class="btn" id="arrow-up-button" >
										<i class="ri-arrow-up-circle-line"></i>
	 								</button>
	 								 </a>  
								</c:if>        
								
                            <!-- arrow down function-->
                            
                             	<c:if test="${sottoscrizione.getPosition()!=6}">
                            	    <c:set var="titleURL3">
				                    		<c:url value="http://localhost:8086/CodeForBreakfast/moveDownPost" >
				                    			<c:param name="SottoscrizioneId" value="${sottoscrizione.getId()}"/>
				                    			  <c:param name="articlesPage" value="${2}"/>   
				                    		</c:url>
		 								</c:set>   	 
									<a href="${titleURL3}" >                                                   
										<button class="btn" id="arrow-down-button" >
	                                		<i class="ri-arrow-down-circle-line"></i>
	                            		</button> 
	 								 </a>  
								</c:if> 
                            
                            
                            </div>

                        <h3 class="title article-title">${sottoscrizione.getPost().getTitle()}</h3>
                        <span>${sottoscrizione.getPost().getData()}</span>
                    </div>
                </div>
                </c:forEach>


   
                
            </div>
            <div class="bottomline-banner2">  
                <a href="articles1.jsp" style="width: 5.5rem"><h3>back</h3></a>
                <a href="articles3.jsp" style="width: 6rem"><h3>next</h3></a> 
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