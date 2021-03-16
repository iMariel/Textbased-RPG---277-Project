import java.io.*;
import java.util.*;
import java.awt.*;
class Main 
{
  public static void main(String[] args) 
  {
    System.out.println("What is your name, Traveler?");
    String name = CheckInput.getString();
    EnemyGenerator g = new EnemyGenerator(); //make gen
    Hero h = new Hero( name ); 
    int choice = 0;
    char c = ' ';
    while ( choice != 5 )
    {
      System.out.println( h.toString() );
      System.out.println( "1. Go North ");
      System.out.println( "2. Go South ");
      System.out.println( "3. Go East ");
      System.out.println( "4. Go West ");
      System.out.println( "5. Quit ");
      choice = CheckInput.getIntRange( 1, 5);
      if ( choice == 1 )
      {
        c = h.goNorth();
      }
      else if ( choice == 2 )
      {
        c = h.goSouth();
      }
      else if ( choice == 3 )
      {
        c = h.goEast();
      }
      else if ( choice == 4 )
      {
        c = h.goWest();
      }
      else
      {
        break;
      }
      if ( c == 'x' )
      {
        System.out.println("Can't go that way, pick a different direction.");
      }
      else if ( c == 'n' )
      {
        System.out.println("There was nothing here.");
      }
      else if ( c == 's' )
      {
        System.out.println("You're back at the start.");
      }
      else if ( c == 'f' )
      {
        System.out.println("You found the exit. Proceeding to the next level.");
        h.levelUp();
      }
      else if ( c == 'i' )
      {
        System.out.println("You found a Health Potion! You drink it to restore your health.");
        h.heal( 25 );
      }
      else if ( c == 'm' )
      {
        Enemy e = g.generateEnemy();
        boolean alive = monsterRoom(h, e);
        if ( alive == false )
        {
          System.out.println(" You have died.");
          break;
        }
      }
    }
    System.out.println("Game Over.");
  }

  public static boolean monsterRoom( Hero h, Enemy e )
  {
    System.out.println( "You've encountered a " + e.getName() );
    int ans = 0;
    boolean alive = true;
    while ( ans != 2 && e.getHp() > 0 && alive == true )
    {
      System.out.println( e );
      System.out.println( "1. Fight ");
      System.out.println( "2. Run Away ");
      ans = CheckInput.getIntRange( 1, 2);
      if ( ans == 1 )
      {
        alive = fight( h , e );
      }
      else
      {
        char r = 'x';
        while ( r == 'x')
        {
          int d = ( int ) (Math.random() * 4) + 1;
          if( d == 1 )
          {
            r = h.goNorth();
          }
          else if ( d == 2 )
          {
            r = h.goSouth();
          }
          else if ( d == 3 )
          {
            r = h.goWest();
          }
          else
          {
            r = h.goEast();
          }
        }
        if ( r == 'f' )
        {
          System.out.println("You found the exit. Proceeding to the next level");
          h.levelUp();
        }
        alive = true;
      }
    }
    return alive;
  }

  public static boolean fight( Hero h, Enemy e )
  {
    System.out.println( "1. Physical Attack ");
    System.out.println( "2. Magic Attack ");
    int ans = CheckInput.getIntRange( 1, 2);
    if ( ans == 1 )
    {
      System.out.println( h.attack( e ) );
    }
    else if ( ans == 2)
    {
      System.out.println( Magical.MAGIC_MENU );
      int spell = CheckInput.getIntRange( 1, 3);
      System.out.println( h.magicAttack(e , spell ) );
    }
    if ( e.getHp() > 0)
    {
      System.out.println( e.attack( h ) );
    }
    else
    {
      System.out.println( "You have defeated the " + e.getName() );
    }
    if ( h.getHp() > 0 )
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}