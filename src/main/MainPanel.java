/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.CustomerSprite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JPanel;
import model.Customer;
import model.Transaction;
import assets.AsssetManager;
import generator.CustomerGenerator;
import java.util.concurrent.CopyOnWriteArrayList;
import queue.QueueManager;

/**
 *
 * @author adoni
 */
public class MainPanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int TILE_SIZE = 48; // 48x48 tile
    private final int MAX_SCREEN_COL = 20;
    private final int MAX_SCREEN_ROW = 12;
    private final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 px
    private final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 px
    
    // FPS
    private int FPS = 60;
    
    // SYSTEM
    private final KeyHandler keyHandler = new KeyHandler(this);
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final AsssetManager tileManager = new AsssetManager(this);
    private final CustomerGenerator customerGenerator = new CustomerGenerator(this);
    private final CustomerSprite customerSprite= new CustomerSprite(this, keyHandler);
    private final SoundManager music = new SoundManager();
    private final SoundManager soundEffect = new SoundManager();
    private final UI ui = new UI(this);
    
    // TRANSACTIONS
    private final List<Transaction> transactions = new ArrayList<>();
    
    // APP THREAD
    private Thread appThread;
    private final QueueManager queueManager = new QueueManager(this);
    private final Queue<Customer>[] customerQueues = new Queue[3]; // window1 (priority), window2, window3
    private final List<Customer> customers = new CopyOnWriteArrayList<>();
    
    
    public MainPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    
    public void setupApp() {
        // Init
        initializeCustomerQueues();
        
        // Generate customers
        CustomerGeneratorThread generatorThread = new CustomerGeneratorThread(this, customerGenerator);
        generatorThread.start();

        // Play music
        playMusic(0);
    }
    
    public void startAppThread() {
        appThread = new Thread(this);
        appThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while (appThread != null) {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if (delta >= 1) {
                // 1 UPDATE: update information such as character position
                update();
                
                // 2 DRAW: draw the screen with the updated information
                repaint();

                delta--;
                drawCount++;
            }
        }
    }
    
    public void update() {
        // Customer Sprite
        for (Customer customer : customers) {
            if (customer != null) {
                customer.getCustomerSprite().update();
            }
        }
        
        // Always check if there is customer that is removed
        queueManager.pollFromQueue();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        tileManager.draw(g2);
        
        for (Customer customer : customers) {
            if (customer != null) {
                customer.getCustomerSprite().draw(g2);
            }
        }
        
        if (keyHandler.isCheckStats()) {
            ui.draw(g2);
            
            for (Customer customer : customers) {
                if (customer != null) {
                    ui.drawCustomerProfile(customer);
                    ui.drawServingTime(customer);
                }
            }
        }
        
        g2.dispose();
    }
    
    // SOUND MANAGER
    public void playMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }
    
    public void stopMusic() {
        music.stop();
    }
    
    public void playSE(int index) {
        soundEffect.setFile(index);
        soundEffect.play();
    }
    
    public void initializeCustomerQueues() {
        for (int i = 0; i < customerQueues.length; i++) {
            customerQueues[i] = new LinkedList();
        }
    }
    
    
    // GETTERS
    public int getTileSize() {
        return TILE_SIZE;
    }

    public int getMaxScreenCol() {
        return MAX_SCREEN_COL;
    }

    public int getMaxScreenRow() {
        return MAX_SCREEN_ROW;
    }

    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public int getFPS() {
        return FPS;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }
    

    public AsssetManager getTileManager() {
        return tileManager;
    }

    public CustomerSprite getCustomerSprite() {
        return customerSprite;
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }

    public SoundManager getMusic() {
        return music;
    }

    public SoundManager getSoundEffect() {
        return soundEffect;
    }

    public UI getUi() {
        return ui;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Thread getAppThread() {
        return appThread;
    }

    public Queue<Customer>[] getCustomerQueues() {
        return customerQueues;
    }
}
