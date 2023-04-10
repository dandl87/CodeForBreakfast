<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ page import="com.protom.codeforbreakfast.model.entity.Msg"%>
   <%@ page import="com.protom.codeforbreakfast.service.ServiceAllConferences"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.Conference"%>
     <%@ page import="com.protom.codeforbreakfast.dbconnections.DbConnectionMySql"%>
  
	  <!DOCTYPE >
		<html lang="en">
			<head>
			    <meta charset="UTF-8">
			    <meta http-equiv="X-UA-Compatible" content="IE=edge">
			    <meta name="viewport" content="width=device-width, initial-scale=1.0">
			    <title>Code for Breakfast | Home</title>
			    <!-- Favicon -->
			    <link rel="icon" type="image/png" sizes="32x32" href="assets/images/favicon2.png">
			    <!-- Remix icons -->
			    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
			    <!-- Swiper.js styles -->
			    <link rel="stylesheet" href="assets/css/swiper-bundle.min.css"/>
			    <!-- Custom styles -->
			    <link rel="stylesheet" href="assets/css/main.css">
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
                        <a href="#" class="list-link current">Home</a>
                    </li>
                    <li class="list-item">
                        <a href="conferences1.jsp" class="list-link">Conferences</a>
                    </li>
                    <li class="list-item">
                        <a href="articles1.jsp" class="list-link">Articles</a>
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
                <button class="btn" id="login-icon">
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


    <!-- Featured articles -->
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
									<c:out value="${infoMsg.getMessage() }"/>
								</c:otherwise>
							</c:choose>	
							</span>
							 
							
						
						
                    <!--  <span class="headline-description">My articles</span> -->
                </div>
                
                <!-- Codice Della Personal Desk sezione Post -->
                <c:set var="count" scope="session" value="${0}"/>
                <c:set var="countInsert" scope="session" value="${0}"/>
                <c:forEach var="sottoscrizione" items="${user.getSottoscrizioniPost()}">
                 
                    <c:if test="${count<6 and countInsert<6}">
                        <c:set var="countInsert" scope="session" value="${countInsert+1}"/>
                        

                        <!-- Articles -->
                       

                            <div class="article featured-article featured-article-${user.getSottoscrizioniPost().get(count).getPosition()}">
                                <img src="${sottoscrizione.getPost().getLinkImg()}" alt="" class="article-image">
                                <a href ="#" class="article-category">${sottoscrizione.getPost().getCategory()}</a>

                                <div class="article-data-container">

                                    <div class="article-data">
                                        
                                        
                                        <!-- remove function-->

                                        <c:set var="titleURL">
							               <c:url value="http://192.168.1.109:8086/CodeForBreakfast/removePost" > 
							                  <c:param name="postId" value="${sottoscrizione.getPost().getId()}"/>   
							                </c:url>  
							           </c:set>
							           
							            <a href="${titleURL}" >  
                                         		
                                            <button class="btn" id="remove-button">
                                                <i class="ri-delete-bin-line"></i>
                                            </button>
                                        </a>

                                        <span class="article-data-spacer"></span> 
                                        
                                        <!-- data of pubblication-->
                                        <span>${sottoscrizione.getPost().getData()}</span> 
                                        
                                       
                                    </div>
                                       <a href="${sottoscrizione.getPost().getLink()}" ><h3 class="title article-title">${sottoscrizione.getPost().getTitle()}</h3> </a>

                                </div>
                            </div> 
                    </c:if>
                    <c:set var="count" scope="session" value="${count+1}"/>
                </c:forEach>
					  
            </div>
            
            <!-- Conferences Right Nav-->
             <div class="sidebar d-grid">

                <h3 class="title featured-content-title">Your Conferences</h3>


                 <!-- Codice Della Personal Desk sezione Conferences  -->
                 <c:set var="count" scope="session" value="${0}"/>
                 <c:forEach var="conferenceSubscription" items="${user.getSottoscrizioniConference()}">
                  <c:set var="count" scope="session" value="${count+1}"/>
                      
                       <!-- Conferences -->
                         <div href="${conferenceSubscription.getConference().getLink()}" class="trending-news-box">
                             <div class="trending-news-img-box">
                                <span class="trending-number place-items-center"><c:out value="${count}" /></span>
                                <a href="${conferenceSubscription.getConference().getLink()}"> <img src="${conferenceSubscription.getConference().getLinkImgSmall()}" alt="" class="article-image"> </a>
                              </div>
                              
                         	<div class="trending-news-data">

                            <div class="article-data"> 
                            
                            
                            <!--  remove Conference -->
                            <c:set var="titleURL1">
		                    	<c:url value="http://192.168.1.109:8086/CodeForBreakfast/removeConference" >
		                    		<c:param name="conferenceId" value="${conferenceSubscription.getConference().getId()}"/>
		                    		<c:param name="conferencesPage" value="${1}"/> 
		                    	</c:url>
		 					</c:set>		                                                         
							 
							<button class="btn" id="remove-button" onClick="callServletWithAjax('${titleURL1}')">
								<i class="ri-delete-bin-line"></i>
							</button> 
							 
							
							<span class="article-data-spacer"></span> 
                            
                               
                                
                                <span><c:out value="${conferenceSubscription.getConference().getTimeOfConference()}" /> </span>

                           </div>

                         <h3 class="title article-title"><c:out value="${conferenceSubscription.getConference().getTitle()}" /></h3>
						
                        </div>
                  </div>
   
                 </c:forEach> 
            </div>

        </div>

    </section>

    <!-- CONFERENCES -->
    
    
    <%			
    			 DbConnectionMySql connection= DbConnectionMySql.getInstance();
    			 connection.avviaConnessione();
                 int pageNumber=1;
                 ServiceAllConferences service = new ServiceAllConferences();
                 ArrayList<Conference> allConferences = service.caricaAllConferencesOfPage(pageNumber);
                 request.setAttribute("NewConferences",allConferences);
                 connection.chiudiConnessione(); 
                 %>
                 
                 
                 
    <section class="quick-read section">

        <div class="container">

            <h2 class="title section-title" data-name="latest conferences">Conferences</h2>
            <!-- Slider main container -->
            <div class="swiper">
                <!-- Additional required wrapper -->
                <div class="swiper-wrapper">
                
                 <!-- Codice Dello Slider -- Conferences  -->
                 <c:set var="count" scope="session" value="${0}"/>
                 <c:forEach var="newConference" items="${NewConferences}">
                  <c:set var="count" scope="session" value="${count+1}"/>
                      
                       <!-- Conferences -->
                        <!-- Slides -->
	                    <a href="${newConference.link}" class="article swiper-slide">
	                        <img src="${newConference.linkImg}" alt="" class="article-image">
	
	                        <div class="article-data-container">
	                            <div class="article-data">
	                                <span><c:out value="${newConference.data}" /></span> 
	                                <span class="article-data-spacer"></span>
	                                <span><c:out value="${newConference.dataConference}" /></span>
	                                <span><c:out value="${newConference.timeOfConference}" /> </span>
	                            </div>
	                           <h3 class="title article-title"><c:out value="${newConference.title}" /></h3>
	                        </div>
	                    </a>
                       
                       
                        
   
                 </c:forEach>
                
                
                   
                     
                </div>
                <!-- Navigation buttons -->
                <div class="swiper-button-prev swiper-controls"></div>
                <div class="swiper-button-next swiper-controls"></div>
                <!-- Pagination -->
                <div class="swiper-pagination"></div>
            </div>

        </div>

    </section>

    <!-- Articles -->
    <section class="older-posts section">

        <div class="container">

            <h2 class="title section-title" data-name="latest articles">Articles</h2>

            <div class="older-posts-grid-wrapper d-grid">

                <a href="#" class="article d-grid">
                    <div class="older-posts-article-image-wrapper">
                        <img src="./assets/images/older_posts/older_posts_1.jpg" alt="" class="article-image">
                    </div>

                    <div class="article-data-container">

                        <div class="article-data">
                            <span>23 Dec 2021</span>
                            <span class="article-data-spacer"></span>
                            <span>3 Min read</span>
                        </div>

                        <h3 class="title article-title">Sample article title</h3>
                        <p class="article-description">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Similique a tempore sapiente corporis, eaque fuga placeat odit voluptatibus.</p>

                    </div>
                </a>

                <a href="#" class="article d-grid">
                    <div class="older-posts-article-image-wrapper">
                        <img src="./assets/images/older_posts/older_posts_2.jpg" alt="" class="article-image">
                    </div>

                    <div class="article-data-container">

                        <div class="article-data">
                            <span>23 Dec 2021</span>
                            <span class="article-data-spacer"></span>
                            <span>3 Min read</span>
                        </div>

                        <h3 class="title article-title">Sample article title</h3>
                        <p class="article-description">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Similique a tempore sapiente corporis, eaque fuga placeat odit voluptatibus.</p>

                    </div>
                </a>

                <a href="#" class="article d-grid">
                    <div class="older-posts-article-image-wrapper">
                        <img src="./assets/images/older_posts/older_posts_3.jpg" alt="" class="article-image">
                    </div>

                    <div class="article-data-container">

                        <div class="article-data">
                            <span>23 Dec 2021</span>
                            <span class="article-data-spacer"></span>
                            <span>3 Min read</span>
                        </div>

                        <h3 class="title article-title">Sample article title</h3>
                        <p class="article-description">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Similique a tempore sapiente corporis, eaque fuga placeat odit voluptatibus.</p>

                    </div>
                </a>

                <a href="#" class="article d-grid">
                    <div class="older-posts-article-image-wrapper">
                        <img src="./assets/images/older_posts/older_posts_4.jpg" alt="" class="article-image">
                    </div>

                    <div class="article-data-container">

                        <div class="article-data">
                            <span>23 Dec 2021</span>
                            <span class="article-data-spacer"></span>
                            <span>3 Min read</span>
                        </div>

                        <h3 class="title article-title">Sample article title</h3>
                        <p class="article-description">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Similique a tempore sapiente corporis, eaque fuga placeat odit voluptatibus.</p>

                    </div>
                </a>

                <a href="#" class="article d-grid">
                    <div class="older-posts-article-image-wrapper">
                        <img src="./assets/images/older_posts/older_posts_5.jpg" alt="" class="article-image">
                    </div>

                    <div class="article-data-container">

                        <div class="article-data">
                            <span>23 Dec 2021</span>
                            <span class="article-data-spacer"></span>
                            <span>3 Min read</span>
                        </div>

                        <h3 class="title article-title">Sample article title</h3>
                        <p class="article-description">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Similique a tempore sapiente corporis, eaque fuga placeat odit voluptatibus.</p>

                    </div>
                </a>

                <a href="#" class="article d-grid">
                    <div class="older-posts-article-image-wrapper">
                        <img src="./assets/images/older_posts/older_posts_6.jpg" alt="" class="article-image">
                    </div>

                    <div class="article-data-container">

                        <div class="article-data">
                            <span>23 Dec 2021</span>
                            <span class="article-data-spacer"></span>
                            <span>3 Min read</span>
                        </div>

                        <h3 class="title article-title">Sample article title</h3>
                        <p class="article-description">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Similique a tempore sapiente corporis, eaque fuga placeat odit voluptatibus.</p>

                    </div>
                </a>

            </div>

            <div class="see-more-container">
                <a href="#" class="btn see-more-btn place-items-center">See more <i class="ri-arrow-right-s-line"></i></i></a>
            </div>

        </div>

    </section>

    <!-- Arguments tags -->
    <section class="popular-tags section">

        <div class="container">

            <h2 class="title section-title" data-name="Arguments tags">Arguments tags</h2>

            <div class="popular-tags-container d-grid">

                <a href="#" class="article">
                    <span class="tag-name">#Technology</span>
                    <img src="./assets/images/tags/travel-tag.jpg" alt="" class="article-image">
                </a>

                <a href="#" class="article">
                    <span class="tag-name">#Java</span>
                    <img src="./assets/images/tags/food-tag.jpg" alt="" class="article-image">
                </a>

                <a href="#" class="article">
                    <span class="tag-name">#OOP</span>
                    <img src="./assets/images/tags/technology-tag.jpg" alt="" class="article-image">
                </a>

                <a href="#" class="article">
                    <span class="tag-name">#Networking</span>
                    <img src="./assets/images/tags/health-tag.jpg" alt="" class="article-image">
                </a>

                <a href="#" class="article">
                    <span class="tag-name">#Design Patterns</span>
                    <img src="./assets/images/tags/nature-tag.jpg" alt="" class="article-image">
                </a>

                <a href="#" class="article">
                    <span class="tag-name">#Data</span>
                    <img src="./assets/images/tags/fitness-tag.jpg" alt="" class="article-image">
                </a>

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
                <span class="copyright-notice">&copy;2023 CodeforBreakfast. All rights reserved.</span>
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
    
     <script type="text/javascript"> 
    function callServletWithAjax(urlTitle){ 
        console.log(urlTitle); 
        
         
		 
    			var xmlHttp = new XMLHttpRequest();
    			
    			xmlHttp.onload = function(urlTitle){ 
    		       	document.location.reload();   
            		    }
        			  
 
    			xmlHttp.open('POST',urlTitle,true);
    	    	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    	    	xmlHttp.send(urlTitle.params); 
		       
    	    	
				}

 
    </script>
    
    
</body>
</html>