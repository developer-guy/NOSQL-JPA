/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nosql_jpa.bean;

import com.mycompany.nosql_jpa.entity.Article;
import com.mycompany.nosql_jpa.entity.Categories;
import com.mycompany.nosql_jpa.entity.Tags;
import com.mycompany.nosql_jpa.service.PersistenceService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Batuhan
 */
@ManagedBean
@ViewScoped
public class NoSQLControllerBean implements Serializable {

    private EntityManager entityManager;
    private String categories;
    private String tags;
    private Article article = new Article();
    private List<Article> articleList = new ArrayList<Article>();

    @PostConstruct
    public void initializeBean() {
        entityManager = PersistenceService.getEntityManager();
        articleList = (List<Article>) entityManager.createQuery("select a from Article a").getResultList();
    }

    public void saveArticle() {
        try {
            entityManager.getTransaction().begin();

            article.getCategoryList().clear();
            article.getTagList().clear();

            String[] arrayCategory = categories.replace(" ", "").split(",");
            for (int i = 0; i < arrayCategory.length; i++) {
                article.getCategoryList().add(new Categories(arrayCategory[i]));
            }

            String[] arrayTags = tags.replace(" ", "").split(",");
            for (int i = 0; i < arrayTags.length; i++) {
                article.getTagList().add(new Tags(arrayTags[i]));
            }

            article.setDate(new Date());

            if (article.getId() == null) {
                entityManager.persist(article);
            } else {
                entityManager.merge(article);
            }

            entityManager.getTransaction().commit();
            articleList = (List<Article>) entityManager.createQuery("select a from Article a").getResultList();
            article = new Article();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            entityManager.getTransaction().rollback();
        }
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

}
