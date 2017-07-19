package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import dbm.ItemManager;
import model.Item;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html";
	public static final String ADD = "ADD";
	public static final String EDIT = "EDIT";
	public static final String UPDATE = "UPDATE";
	public static final String REMOVE = "REMOVE";
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	protected void handleAddItem(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// create a new instance of Item
		Item item = new Item(
				request.getParameter("name"), 
				request.getParameter("type"), 
				Integer.parseInt(request.getParameter("price")), 
				new Date(Integer.parseInt(request.getParameter("year")), 
						Integer.parseInt(request.getParameter("month")), 
						Integer.parseInt(request.getParameter("day"))
						)
				);
		// add item to db
		if(ItemManager.addItem(item) != -1) {
			response.sendRedirect("index.jsp");
		} else {
			response.setContentType(CONTENT_TYPE);
			response.getWriter().print("uh oh");
		}		
	}
	
	/**
	 * Retrieves data of the item to be selected, and send the object to populate a form 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SuppressWarnings("deprecation")
	protected void handleEditItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		int id = Integer.parseInt(request.getParameter("id"));
		Item item = ItemManager.getItemById(id);
		
		if(item == null) {
			System.err.println("No item id " + id);
			System.exit(-1);
		}
		
		System.out.println("[DEBUG: editing item id= "+ id + "]");
		
		request.setAttribute("name", item.getName());
		request.setAttribute("type", item.getType());
		request.setAttribute("month", item.getDateAquired().getMonth());
		request.setAttribute("day", item.getDateAquired().getDay());
		request.setAttribute("year", item.getDateAquired().getYear());
		request.setAttribute("price", item.getPrice());
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("/edit-item.jsp").forward(request, response);
		
	}

	@SuppressWarnings("deprecation")
	private void handleUpdateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Item item = new Item(
				request.getParameter("name"), 
				request.getParameter("type"), 
				Integer.parseInt(request.getParameter("price")), 
				new Date(Integer.parseInt(request.getParameter("year")), 
						Integer.parseInt(request.getParameter("month")), 
						Integer.parseInt(request.getParameter("day"))
						)
				);
		item.setId(id);
		
		if(ItemManager.updateItem(item)!=-1) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			System.err.println("Failed to update item");
		}
	}
	

	protected void handleRemoveItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(ItemManager.removeItem(id) != -1) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			System.err.println("Failed to update item");
		}
		
	}

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
		System.out.println("[DEBUG]: Request URI " + request.getRequestURI());
		
		if(ADD.equals(request.getParameter("command"))) {
			// add item
			handleAddItem(request, response);
		} else if(EDIT.equals(request.getParameter("command"))) {
			// retrieve all items
			handleEditItem(request, response);
		} else if(UPDATE.equals(request.getParameter("command"))) {
			// retrieve all items
			handleUpdateItem(request, response);
		} else if(REMOVE.equals(request.getParameter("command"))) {
			handleRemoveItem(request, response);
		} else {
			// error
		}
		
	}

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
		doGet(request, response);
	}

}