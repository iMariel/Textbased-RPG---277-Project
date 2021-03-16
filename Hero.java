import java.util.*;
import java.awt.Point;

/**Hero.java - Representation of the player*/
public class Hero extends Entity implements Magical{
  private Map map = new Map(); //textual representation of map layout
  private Point loc; //textual representation of current location on map
  private int level;  //representation of map level

  /**Initializes hero's starting location
  @param n Hero's name*/
  public Hero (String n)
  {
    super( n, 25 );
    map.loadMap(1);
    level = 1;
    loc = new Point( map.findStart() );
    map.reveal(loc);
  }

  @Override
  public String toString()
  {
    return "\n" + getName() + "\t|\tLevel: " + level +"\nHP: " + getHp()+ "/" + getMaxHP() + "\n" + map.mapToString(loc);
  }

  /**Loads in new map after each level*/
  public void levelUp()
  {
    level++;
    int num = level;
    if ( num > 3 )
    {
      num = level % 3;
      if ( num == 0 )
      {
        num = 3;
      }
    }
    map.loadMap(num);
    map.reveal(loc);
  }

  /**Moves hero in the north direction
  @return the new current location of hero (up one line)*/
  public char goNorth()
  { 
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_y -= 1;
    if ( temp_y < 0 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      map.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      if ( letter != 'f' && letter != 's' )
      {
        map.removeCharAtLoc(loc);
      }
      return letter;
    }
  }
  /**Moves hero in the south direction
  @return the new current location of hero (down one line)*/
  public char goSouth()
  {
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_y += 1;
    if ( temp_y > 4 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      map.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      if ( letter != 'f' && letter != 's' )
      {
        map.removeCharAtLoc(loc);
      }
      return letter;
    }
  }
  /**Moves hero in the east direction
  @return the new current location of hero (next character)*/
  public char goEast()
  {
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_x += 1;
    if ( temp_x > 4 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      map.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      if ( letter != 'f' && letter != 's' )
      {
        map.removeCharAtLoc(loc);
      }
      return letter;
    }
  }
  /**Moves hero in the west direction
  @return the new current location of hero (previous character)*/
  public char goWest()
  {
    double temp_x = loc.getX();
    double temp_y = loc.getY();
    temp_x -= 1;
    if ( temp_x < 0 )
    {
      return 'x';
    }
    else
    {
      loc.setLocation(temp_x, temp_y);
      map.reveal(loc);
      char letter = map.getCharAtLoc(loc);
      if ( letter != 'f' && letter != 's' )
      {
        map.removeCharAtLoc(loc);
      }
      return letter;
    }
  }

  /**Representation of hero's physical attack
   @param e enemy entity that takes damage from hero's attack*/
  @Override
  public String attack(Entity e)
  {
    int atk = ( int ) (Math.random() * 5) + 1;
    e.takeDamage( atk );
    return this.getName() + " attacks " + e.getName() + " for " + atk + " damage ";
  }

  /**Representation of hero's magic attack based on what spell they want
    @param e enemy entity that takes damage from hero's spell
    @param i is the number corresponding with spell */
  public String magicAttack(Entity e, int i)
  {
    String line = ""; 
    if ( i == 1)
    {
      line = this.magicMissle(e);
    }
    else if ( i == 2)
    {
      line = this.fireball(e);
    }
    else if ( i == 3 )
    {
      line = this.thunderclap(e);
    }
    return line;
  }

  /**Glowing darts of magical force 
    @param e will be enemy*/
  @Override
  public String magicMissle(Entity e)
  {
    int atk = ( int ) (Math.random() * 5) + 3;
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Magic Missile.";
  }
  /**A bright streak of explosive flame 
    @param e will be enemy*/
  @Override
  public String fireball(Entity e)
  {
    int atk = ( int ) (Math.random() * 3) + 4;
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Fireball.";
  }
  /**A mighty burst of thunder 
    @param e will be enemy*/
  @Override
  public String thunderclap(Entity e)
  {
    int atk = ( int ) (Math.random() * 7) + 3;
    e.takeDamage( atk );
    return this.getName() + " zaps " + e.getName() + " for " + atk + " damage with Thunderclap.";
  }
}