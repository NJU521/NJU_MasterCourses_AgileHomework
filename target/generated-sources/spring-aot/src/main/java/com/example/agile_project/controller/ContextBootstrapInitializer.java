package com.example.agile_project.controller;

import com.example.agile_project.service.BaiduAccessTokenService;
import com.example.agile_project.service.ImageDetectionService;
import com.example.agile_project.service.ImageSaveService;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public final class ContextBootstrapInitializer {
  public static void registerUserController(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("userController", UserController.class)
        .instanceSupplier((instanceContext) -> {
          UserController bean = new UserController();
          instanceContext.field("imageSaveService", ImageSaveService.class)
              .invoke(beanFactory, (attributes) -> bean.imageSaveService = attributes.get(0));
              instanceContext.field("baiduAccessTokenService", BaiduAccessTokenService.class)
                  .invoke(beanFactory, (attributes) -> bean.baiduAccessTokenService = attributes.get(0));
                  instanceContext.field("imageDetectionService", ImageDetectionService.class)
                      .invoke(beanFactory, (attributes) -> bean.imageDetectionService = attributes.get(0));
                      return bean;
                    }).register(beanFactory);
              }
            }
