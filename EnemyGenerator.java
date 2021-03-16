import java.util.*;
import java.io.*;
/** EnemyGenerator.java - creates enemies to fight against*/
public class EnemyGenerator{
  private ArrayList<String> enemyList = new ArrayList<String>(); //list of enemies created

  /**Loads in "Enemies.txt" and adds each line into enemyList*/
  public EnemyGenerator()
  {
    try
    {
      Scanner read = new Scanner( new File( "Enemies.txt" ) );
      while( read.hasNext() ) 
      {
        String line = read.nextLine(); 
        enemyList.add(line);
      }
      read.close();
    }
    catch (FileNotFoundException fnf) //for if the file is not found
    {
      System.out.println( "File was not found" );
    }
  }

  /**If the hero lands on a monster trigger spot, then a random
  * monster from enemyList will appear.
  */
  public Enemy generateEnemy()
  {
    int index = ( int )( Math.random() * enemyList.size());
    String enem = enemyList.get( index );
    String [] tokens = enem.split(","); //splits enemy's name and Hp, respectively
    String n = tokens[0];
    int hp = Integer.parseInt( tokens[1] );
    int rand = ( int )( Math.random() * 2) + 1;//determines magical enemy or not.
    if( rand == 1 )
    {
      n = "Magical " + n;
      MagicalEnemy enemy = new MagicalEnemy(n , hp) ;
      return enemy;
    }
    else
    {
      Enemy enemy = new Enemy(n , hp) ;
      return enemy;
    }
  }
}