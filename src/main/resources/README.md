### Documentation:

Please commit the following to this `README.md`:

1. Instructions on how to build/run your application

1. Clone the repository
2. DB setup : 1. Change the JPA datasource properties & Hibernate Dialect in "application.yml" to the DB that you are using.
              2. Run SQL provided in resources folder in your DB.
3.Gradle:     3. Windows: install gradle, set JAVA_HOME and Path environment variable to include java command on path
              4. Build application using - gradlew.bat build bootJar
              5. Start tomcat using - gradlew.bat build bootRun
              6. Server starts on port 8093
4. APIs:
    POST: http://localhost:8093/wave/time-report/upload
           Request parameter: file 
           Value: CSV file
            
    GET: http://localhost:8093/wave/payload-report
                
1. Answers to the following questions:
   - How did you test that your implementation was correct?
        I tested manually. By calling APIs, and cross-checking output against requirements.
        
   - If this application was destined for a production environment, what would you add or change?
        - With better understanding of application architecture, I will change the DB choice and data organization.
        - I would fix a design issue in Entity classes : EmployeeReport and PayPeriod. I would change static references to collections to object level.
          
   - What compromises did you have to make as a result of the time constraints of this challenge?
        - I could not add Junits. 
        
## Submission Instructions

1. Clone the repository.
1. Complete your project as described above within your local repository.
1. Ensure everything you want to commit is committed.
1. Create a git bundle: `git bundle create your_name.bundle --all`
1. Email the bundle file to [dev.careers@waveapps.com](dev.careers@waveapps.com) and CC the recruiter you have been in contact with.
