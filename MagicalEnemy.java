/**MagicalEnemy.java - Representation of a magical enemy, extension of
Enemy and implements Magical*/
public class MagicalEnemy extends Enemy implements Magical
{
  /**Initiaizes Magical enemy's name and max HP
    @param n name of magical enemy
    @param mhp max HP of Magical enemy*/
  public MagicalEnemy(String n, int mhp)
  {
    super(n, mhp);
  }

  /**Glowing darts of magical force 
    @param e will be hero*/
  @Override
  public String magicMissle(Entity e)
  {
    int atk = ( int ) (Math.random() * 5) + 3;
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Magic Missile.";
  }
  /**A bright streak of explosive flame 
    @param e will be hero*/
  @Override
  public String fireball(Entity e)
  {
    int atk = ( int ) (Math.random() * 3) + 4;
    e.takeDamage( atk );
    return this.getName() + " hits " + e.getName() + " for " + atk + " damage with Fireball.";
  }
  /**A mighty burst of thunder 
    @param e will be hero*/
  @Override
  public String thunderclap(Entity e)
  {
    int atk = ( int ) (Math.random() * 7) + 3;
    e.takeDamage( atk );
    return this.getName() + " zaps " + e.getName() + " for " + atk + " damage with Thunderclap.";
  }

  /**Representation of Magical enemy's attack (utlizes Magical interface)
    @param e representation of magical enemy*/
  public String attack( Entity e )
  {
    String line = ""; 
    int num = ( int ) (Math.random() * 3) + 1;
    if ( num == 1 )
    {
      line = magicMissle( e );
    }
    else if ( num == 2 )
    {
      line = fireball( e );
    }
    else if ( num == 3 )
    {
      line = thunderclap( e );
    }
    return line;
  }
}