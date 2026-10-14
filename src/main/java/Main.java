import java.util.Date;

import model.ModelException;
import model.Post;
import model.User;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;

public class Main {
	 public static void main(String[] args) throws ModelException{
		 // instancinado DAO
		 UserDAO userDAO = DAOFactory.createDAO(UserDAO.class);
		 PostDAO postDAO = DAOFactory.createDAO(PostDAO.class);
		 
		
		 //Inserção
		 User bill = new User();
		 bill.setName("Bill Gatos");
		 bill.setGender("M");
		 bill.setEmail("bill@gmail.com");
		 bill.setPassword("1234");
		 //userDAO.save(bill);

		 
		 Post p =new Post(7);
		 p.setContent("Teste insercao");
		 p.setPostDate(new Date());
		 p.setUser(new User(4));
		 //postDAO.save(p);;
//		 
		 //Alteração
		 bill.setId(6);
		 bill.setName("Bill Gates");
		 //userDAO.update(bill);
		 
		 p.setContent("Teste alteração");
		 p.setUser(new User(6));
		 //postDAO.update(p);
//		 
//		 //Exclusão
		 bill.setId(7);
		 //userDAO.delete(bill);
		 
		 //postDAO.delete(p);
		 
		 //Consulta		 
//		 for(User user : userDAO.listAll()) {
//			 System.out.printf("Nome %s, Sex %s, Email %s\n", user.getName(), user.getGender(), user.getEmail());
//		 }
		 
		 for(Post post : postDAO.listAll()) {
			 System.out.printf("Conteudo %s, Data %s, Usuario %s\n", post.getContent(), post.getPostDate(), post.getUser());
		 }
	 }
}
