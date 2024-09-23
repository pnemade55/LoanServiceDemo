# LoanServiceDemo

- Application Base Url: http://localhost:8081/loan-service-demo/

- [Health Url](http://localhost:8081/loan-service-demo/ping)

- [Swagger Url](http://localhost:8081/loan-service-demo/swagger-ui/index.html)

- [H2 DB Consol Url](http://localhost:8081/loan-service-demo/h2-console/login.jsp)
  `url: jdbc:h2:mem:loanapp | username: admin| password: root@123`


- <p>please refer below insomnia collection for accessing APIs:<br>Insomnia_2024-09-22.json </p>


- Project structure:
<br><img src="images/1.png" width="300" />

- Build docker image:
<br><img src="images/2.png" width="300" />
- start minikube:
<br><img src="images/3.png" width="300" />
- deploy on loan service to k8s and see ip and ports:
<br><img src="images/4.png" width="300" />
- check logs to see if app is up
<br><img src="images/5.png" width="300" />
- Also you can check with ping url
<br><img src="images/5a.png" width="300" />
- Postman/insomia request1(to apply the loan): v1/loan/apply
<br><img src="images/6.png" width="300" />
- Postman/insomia request2 (to get all customers): v1/customer
<br><img src="images/7.png" width="300" />
- Postman/insomia request3 (to get SumOfTotalAppliedLoans By any customer): v1/loan/summary/1 
 <br><img src="images/8.png" width="300" />

