# Spring Data Jpa Multiple Database configuration example

> It includes repository filtering by annotations using `@EnableJpaRepositories(includeFilters = ...)`
>> It also includes Liquibase configuration for one of the databases

Just replace ${db.primary.*}/${db.secondary.*} in [application.yml](src%2Fmain%2Fresources%2Fapplication.yml) with your
databases credentials.

Here you can see how to use *annotation filters for your
repositories*: [example](src%2Fmain%2Fjava%2Fcom%2Fshutuper%2Fspringdatajpamultipledb%2Fexample)

If you want mark your method as @Transactional you need to specify proper transactionManager (based on the db you are
calling), for example:

```
@Transactional("primaryDataBaseTransactionManager")
public void deleteById(UUID id) {
    primaryDBEntityRepository.deleteById(id);
}
```