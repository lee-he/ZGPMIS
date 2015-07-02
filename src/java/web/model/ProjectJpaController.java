/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import web.model.exceptions.NonexistentEntityException;
import web.model.exceptions.PreexistingEntityException;

/**
 *
 * @author User
 */
public class ProjectJpaController implements Serializable {

    public ProjectJpaController() {
        emf = Persistence.createEntityManagerFactory("ZGPMIS2PU");
    }

    public ProjectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Project project) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProject(project.getName()) != null) {
                throw new PreexistingEntityException("Project " + project + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Project project) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            project = em.merge(project);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = project.getName();
                if (findProject(id) == null) {
                    throw new NonexistentEntityException("The project with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Project project;
            try {
                project = em.getReference(Project.class, id);
                project.getName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The project with id " + id + " no longer exists.", enfe);
            }
            em.remove(project);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Project> findProjectEntities() {
        return findProjectEntities(true, -1, -1);
    }

    public List<Project> findProjectEntities(int maxResults, int firstResult) {
        return findProjectEntities(false, maxResults, firstResult);
    }

    private List<Project> findProjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Project.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Project findProject(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Project.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Project> rt = cq.from(Project.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Map> list() {
        ProjectJpaController projectJpaController = new ProjectJpaController();
        List<Project> projectList = projectJpaController.findProjectEntities();
        List<Map> resultList = new ArrayList<>();
        projectList.stream().forEach((project) -> {
            Map map = new HashMap();
            map.put("name", project.getName());
            map.put("owner", project.getOwner());
            map.put("budget", project.getBudget());
            map.put("duedate", (new SimpleDateFormat("yyyy/MM/dd")).format(project.getDuedate()));
            map.put("tel", project.getTel());
            map.put("address", project.getAddress());
            resultList.add(map);
        });
        return resultList;
    }

    public List<Map> list(int maxResults, int firstResult) {
        ProjectJpaController projectJpaController = new ProjectJpaController();
        List<Project> projectList = projectJpaController.findProjectEntities(maxResults, firstResult);
        List<Map> resultList = new ArrayList<>();
        projectList.stream().forEach((project) -> {
            Map map = new HashMap();
            map.put("name", project.getName());
            map.put("owner", project.getOwner());
            map.put("budget", project.getBudget());
            map.put("duedate", (new SimpleDateFormat("yyyy/MM/dd")).format(project.getDuedate()));
            map.put("tel", project.getTel());
            map.put("address", project.getAddress());
            resultList.add(map);
        });
        return resultList;
    }
}
