/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.configuration;

import com.example.user.User;
import com.example.user.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 *
 * @author msol-pc
 */
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public UserRepository repo;

    @Bean
    public FlatFileItemReader<User> fileReader() throws Exception {

        return new FlatFileItemReaderBuilder<User>()
                .name("file-reader")
                .resource(new FileSystemResource("C:/test.csv"))
                .targetType(User.class)
                .delimited().delimiter(",").names(new String[]{"firstName", "username", "password"})
                .build();
    }

    @Bean
    public RepositoryItemWriter repoWriter() {
        RepositoryItemWriter writer = new RepositoryItemWriter();
        writer.setRepository(repo);
        writer.setMethodName("save");
        return writer;
    }

    public Job importUserJob() throws Exception {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() throws Exception {
        return stepBuilderFactory.get("step")
                .<User, User>chunk(5)
                .reader(fileReader())
                .writer(repoWriter())
                .build();
    }
}
