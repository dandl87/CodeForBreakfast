<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ page import="com.protom.codeforbreakfast.service.ServiceMsg"%>
  <%@ page import="com.protom.codeforbreakfast.model.entity.Msg"%>
  
 
  
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
		                        <a href="/CodeForBreakfast/conferences?page=1" class="list-link">Conferences</a>
		                    </li>
		                    
		                    <li class="list-item">
		                        <a href="/CodeForBreakfast/articles?page=1" class="list-link">Articles</a>
		                    </li>
		                    
		                    <li class="list-item">
		                        <a href="#" class="list-link">News</a>
		                    </li>
		                    <li class="list-item">
		                        <a href="/CodeForBreakfast/membership" class="list-link">Membership</a>
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
		                <button class="btn" id="login-icon">
		                    <i class="ri-login-circle-fill"></i>
		                </button>
		
		                <!-- Log Out Icon -->
		                <a href="http://192.168.1.109:8086/CodeForBreakfast/logout">
		                	<button class="btn" id="logout-button">
		                    	<i class="ri-logout-circle-r-line"></i>
		                     </button>
		                </a>
		                 
						<!-- Sing Up -->
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
		           <input class="form-input" type="password" placeholder="password" name="password">
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
											Please log in to view your personal desk!
										</c:when>
										 
										<c:when test='${infoMsg.getFromSection().equals("Desk")}'> 
											<c:out value="${infoMsg.getMessage()}"/>
										</c:when>
										<c:otherwise>
											<c:out value=""/>
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
		                        
		                            <div class="article-desk featured-article featured-article-${user.getSottoscrizioniPost().get(count).getPosition()}">
		                            	<c:if test="${sottoscrizione.getPosition()<5}">
		                                <img src="${sottoscrizione.getPost().getLinkImg()}_1.jpg" alt="" class="article-image">
		                                </c:if>
		                                <c:if test="${sottoscrizione.getPosition()>=5}">
		                                <img src="${sottoscrizione.getPost().getLinkImg()}_2.jpg" alt="" class="article-image">
		                                </c:if>
		                                
		                                <a href ="#" class="article-category">${sottoscrizione.getPost().getCategory()}</a>
		
		                                <div class="article-data-container">
		
		                                    <div class="article-data">
		                                    	<!-- view function-->
												<c:choose>
												<c:when test="${articleOnScreenInSession==sottoscrizione.getPost().getLink()}">
		                                         <c:set var="onScreenURL">
									               <c:url value="http://192.168.1.109:8086/CodeForBreakfast/onScreenClose" >  
									                </c:url>  
									           	</c:set> 
														<button class="btn" id="view-button" onClick="articleOnScreenFunctionSetNull('${onScreenURL}')">
		                                                 <i class="ri-book-open-fill"></i>
		                                           		</button>
												</c:when>
													
													<c:otherwise>
													<c:set var="onScreenURL">
									               <c:url value="http://192.168.1.109:8086/CodeForBreakfast/onScreen" > 
									                  <c:param name="articleLinkToView" value="${sottoscrizione.getPost().getLink()}"/>
									                   <c:param name="articleTitleToView" value="${sottoscrizione.getPost().getTitle()}"/>  
									                </c:url>  
									           	</c:set> 
														<button class="btn" id="view-button" onClick="articleOnScreenFunction('${onScreenURL}')">
														<i class="ri-folder-3-fill"></i>
		                                                
		                                           		</button>
													
													</c:otherwise>
												</c:choose>	 
		                                       
		                                        
		                                        <!-- remove function-->
		
		                                        <c:set var="titleURL">
									               <c:url value="http://192.168.1.109:8086/CodeForBreakfast/removePost" > 
									                  <c:param name="postId" value="${sottoscrizione.getPost().getId()}"/>   
									                </c:url>  
									           </c:set>
		                                            <button class="btn" id="remove-button" onClick="callServletWithAjax('${titleURL}')">
		                                                <i class="ri-delete-bin-line"></i>
		                                            </button>
		                                        
		                                        
		                                         
		                                         <!-- arrow up function-->
			                              	  <c:if test="${sottoscrizione.getPosition()!=1}">
			                            	    <c:set var="titleURL2">
							                    		<c:url value="http://192.168.1.109:8086/CodeForBreakfast/moveUpPost" >
							                    			<c:param name="SottoscrizioneId" value="${sottoscrizione.getId()}"/>
							                    			  <c:param name="articlesPage" value="${1}"/>   
							                    		</c:url>
					 								</c:set>   	 
												                                                  
												<button class="btn" id="arrow-up-button" onClick="callServletWithAjax('${titleURL2}')">
													<i class="ri-arrow-up-circle-line"></i>
				 								</button>
				 								  
											</c:if>  
											<c:if test="${sottoscrizione.getPosition()==1 }">
											<span class="article-data-spacer"></span> 
		                                    </c:if>       
											
			                            <!-- arrow down function-->
			                            
			                             	<c:if test="${sottoscrizione.getPosition()!=6}">
			                            	    <c:set var="titleURL3">
							                    		<c:url value="http://192.168.1.109:8086/CodeForBreakfast/moveDownPost" >
							                    			<c:param name="SottoscrizioneId" value="${sottoscrizione.getId()}"/>
							                    			  <c:param name="articlesPage" value="${1}"/>   
							                    		</c:url>
					 								</c:set>   	 
												                                                 
													<button class="btn" id="arrow-down-button" onClick="callServletWithAjax('${titleURL3}')">
				                                		<i class="ri-arrow-down-circle-line"></i>
				                            		</button> 
				 								  
											</c:if>  
											<c:if test="${sottoscrizione.getPosition()==6 }">
											<span class="article-data-spacer"></span> 
		                                    </c:if> 
		                                        
		                                        
		                                       
		                                    </div>
		                                        <h3 class="title article-title">${sottoscrizione.getPost().getTitle()}</h3><!--  </a> -->
												<!-- data of pubblication-->
		                                        <span>${sottoscrizione.getPost().getData()}</span> 
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
		                         <div class="trending-news-box">
		                             <div class="trending-news-img-box">
		                                <span class="trending-number place-items-center"><c:out value="${count}" /></span>
		                                <a href="/CodeForBreakfast/conference?id=${conferenceSubscription.getConference().getId()}"> <img src="${conferenceSubscription.getConference().getLinkImgSmall()}" alt="" class="article-image"> </a>
		                              </div>
		                              
		                         	<div class="trending-news-data">
		
		                            <div class="article-data"> 
		                            
		                            
		                            <!--  remove Conference -->
		                            <c:set var="titleURL1">
				                    	<c:url value="http://192.168.1.109:8086/CodeForBreakfast/removeConference" >
				                    		<c:param name="conferenceId" value="${conferenceSubscription.getConference().getId()}"/>
				                    	</c:url>
				 					</c:set>		                                                         
									 
									<button class="btn" id="remove-button" onClick="callServletWithAjax('${titleURL1}')">
										<i class="ri-delete-bin-line"></i>
									</button> 
									 
									
									<span class="article-data-spacer"></span> 
		                            
		                               
		                                
		                                <span><c:out value="${conferenceSubscription.getConference().getTimeOfConference()}" /> </span>
		
		                           </div>
		
		                      <h3 class="title article-title"> <a href="/CodeForBreakfast/conference?id=${conferenceSubscription.getConference().getId()}"> <c:out value="${conferenceSubscription.getConference().getTitle()}" /> </a> </h3>
								
		                        </div>
		                  </div>
		   
		                 </c:forEach> 
		            </div>
		
		        </div>
		
		    </section>
		    
		    <!--  Sezione Articolo Vista -->
		
			<div>
			
			<c:if test="${articleOnScreenInSession != null}" >
			   <jsp:include page="${articleOnScreenInSession}" /> 
			</c:if>
			
			</div>
		 	 
		 	 <!--  Sezione Articolo Vista  FINE -->
		
		    
		
		    
		
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
		                <span>Front-End developed by <a href="https://www.youtube.com/c/JulioCodes">JulioCode</a></span>
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
		    
		     <script type="text/javascript"> 
		     
				    function articleOnScreenFunction(onScreenURL){  
						var xmlHttp = new XMLHttpRequest();
		
				    	xmlHttp.onload = function(onScreenURL){ 
				    		document.location.reload();   
							}
		
						xmlHttp.open('POST',onScreenURL,true);
						xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
						xmlHttp.send(onScreenURL.params); 
		
				    } 
		    </script>
		    
		    <script type="text/javascript"> 
		     
				    function articleOnScreenFunctionSetNull(onScreenURL){  
						var xmlHttp = new XMLHttpRequest();
		
				    	xmlHttp.onload = function(onScreenURL){ 
				    		document.location.reload();   
							}
		
						xmlHttp.open('POST',onScreenURL,true);
						xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
						xmlHttp.send(); 
		
				    } 
		    </script>
		    
		    
		</body>
</html>