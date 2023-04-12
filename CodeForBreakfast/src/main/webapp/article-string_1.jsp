 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="com.protom.codeforbreakfast.dbconnections.DbConnectionMySql"%>
    <%@ page import="com.protom.codeforbreakfast.service.ServicePost"%>
    <%@ page import="com.protom.codeforbreakfast.model.entity.Post"%> 
    
    <%		                 
    DbConnectionMySql connection= DbConnectionMySql.getInstance();
	connection.avviaConnessione(); 
	ServicePost service = new ServicePost();
	Post actualPost = service.cercaPost(15);
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