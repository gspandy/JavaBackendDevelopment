package com.lavor.spring.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 编程式事务，在实际应用中很少使用
 * Created by lei.zeng on 2017/7/4.
 */
public class ProgrammingTransaction {
    public static void main(String[] args){
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("transaction/programming-transaction.xml");
        //根据应用程序上下文，来获取其中的bean
        TransactionTemplate transactionTemplate= (TransactionTemplate) context.getBean("transactionTemplate");
        //根据应用程序上下文，来获取其中的bean
        final JdbcTemplate jdbcTemplate= (JdbcTemplate) context.getBean("jdbcTemplate");
        Integer result = transactionTemplate.execute(
                new TransactionCallback<Integer>(){
                    /**
                     * 该方法处于事务中
                     */
                    @Override
                    public Integer doInTransaction(TransactionStatus transactionStatus) {
                        try {
                            jdbcTemplate.update("insert into user values (3,'zhangfei')");
                            //两次相同的操作，将违反主键约束，事务操作时最终结果是数据没有插进去
                            jdbcTemplate.update("insert into user values (3,'zhangfei')");
                        }catch (Exception e){
                            //事务回滚
                            transactionStatus.setRollbackOnly();
                        }
                        return 1;
                    }

                }); // 执行execute方法进行事务管理
    }
}
