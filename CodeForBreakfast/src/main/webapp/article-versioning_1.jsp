<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="com.protom.codeforbreakfast.dbconnections.DbConnectionMySql"%>
    <%@ page import="com.protom.codeforbreakfast.service.ServicePost"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.Post"%>
    
     <%   if(session.getAttribute("user")==null)
   			 response.sendRedirect("index.jsp"); 
	 %>
    
    <!DOCTYPE >
		<html lang="en">
		<head>
		    <meta charset="UTF-8">
		    <meta http-equiv="X-UA-Compatible" content="IE=edge">
		    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		    <title>Code for Breakfast | Versioning </title>
		    <!-- Favicon -->
		    <link rel="icon" type="image/png" sizes="32x32" href="assets/images/favicon2.png">
		    <!-- Remix icons -->
		    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
		    <!-- Swiper.js styles -->
		    <link rel="stylesheet" href="assets/css/swiper-bundle.min.css"/>
		    <!-- Custom styles -->
		    <link rel="stylesheet" href="assets/css/article.css">
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
		                        <a href="conferences1.jsp" class="list-link">Conferences</a>
		                    </li>
		                    <li class="list-item">
		                        <a href="articles1.jsp" class="list-link current">Articles</a>
		                    </li>
		                    <li class="list-item">
		                        <a href="#" class="list-link">News</a>
		                    </li>
		                    <li class="list-item">
		                        <a href="membership.jsp" class="list-link">Membership</a>
		                    </li>
		                    <li class="list-item">
		                        <a href="#" class="list-link">Contact</a>
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
		    
		   
		    
		    <!-- The Conference -->
		          	<%
			                 DbConnectionMySql connection= DbConnectionMySql.getInstance();
			    			 connection.avviaConnessione(); 
			                 ServicePost service = new ServicePost();
			                 Post actualPost = service.cercaPost(1);
			                 request.setAttribute("post",actualPost);
			                 connection.chiudiConnessione();
			                 %>
		    
			<section class="blog-post section-header-offset">
		        <div class="blog-post-container container">
		            <div class="blog-post-data">
		                <h3 class="title blog-post-title">${post.title}</h3>
		                <div class="article-data">
		                    <span>${post.data}</span>
		                    <span class="article-data-spacer"></span>
		                    <span>${post.subTitle}</span>
		                </div>
		                <img src="${post.linkImgBanner}" alt="">
		            </div>
		
		            <div class="container">
		                <p>
		                    Lorem, ipsum dolor sit amet consectetur adipisicing elit. Debitis eius possimus hic eligendi distinctio rerum incidunt, esse quasi eum molestiae ducimus ipsam quae, aliquid ullam placeat dolorum nulla vero. Quam? Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sapiente repellat consequatur culpa, repudiandae aut dolores iusto. Rem natus soluta, dolores, ad deleniti, aut dolorem corrupti quasi amet unde delectus hic?
		                </p>
		                
		                <p>
		                    Lorem, ipsum dolor sit amet consectetur adipisicing elit. Debitis eius possimus hic eligendi distinctio rerum incidunt, esse quasi eum molestiae ducimus ipsam quae, aliquid ullam placeat dolorum nulla vero. Quam? Lorem ipsum dolor sit amet consectetur, adipisicing elit. Vero quod necessitatibus, aspernatur pariatur asperiores earum quas adipisci veritatis quidem facilis! Nihil veniam quaerat nulla possimus, asperiores vero voluptatum placeat. Eveniet!
		                </p>
		
		                <blockquote class="quote">
		                    <p><span><i class="ri-double-quotes-l"></i></span> Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia voluptates, laboriosam voluptatum quos non consequuntur nesciunt necessitatibus tempora quod inventore corporis rem nihil itaque, at provident minus aliquam veritatis. Labore?  <span><i class="ri-double-quotes-r"></i></span></p>
		                </blockquote>
		            
		                <p>
		                    Lorem, ipsum dolor sit amet consectetur adipisicing elit. Debitis eius possimus hic eligendi distinctio rerum incidunt, esse quasi eum molestiae ducimus ipsam quae, aliquid ullam placeat dolorum nulla vero. Quam? Lorem ipsum dolor sit amet consectetur, adipisicing elit. Vero quod necessitatibus, aspernatur pariatur asperiores earum quas adipisci veritatis quidem facilis! Nihil veniam quaerat nulla possimus, asperiores vero voluptatum placeat. Eveniet!
		                </p>
		
		                
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
		
		  
		    
		   	<script async src="https://www.youtube.com/iframe_api"></script>
			<script>
				function onYouTubeIframeAPIReady() {
					var player;
					player = new YT.Player('muteYouTubeVideoPlayer', {
					videoId: 'zHiWqnTWsn4', // YouTube Video ID
					width: 560, // Player width (in px)
					height: 316, // Player height (in px)
					playerVars: {
						autoplay: 1, // Auto-play the video on load
						controls: 1, // Show pause/play buttons in player
						showinfo: 0, // Hide the video title
						modestbranding: 1, // Hide the Youtube Logo
						loop: 1, // Run the video in a loop
						//fs: 0, // Hide the full screen button
						cc_load_policy: 0, // Hide closed captions
						iv_load_policy: 3, // Hide the Video Annotations
						autohide: 0, // Hide video controls when playing
						},
					events: {
					onReady: function (e) {
						e.target.mute();
						},
					},
					});
				}
									
			// Written by @labnol
			</script>
		</body>
		</html>