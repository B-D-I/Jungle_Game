package game;

public abstract class GameObject extends Model{

    private boolean alive;
    // object projectile array
    int[] projectileXAxis;
    int[] projectileYAxis;

    public GameObject(){
    }
    // getter methods for X and Y locations of projectile
    public int[] getProjectileYAxis() {
        return projectileYAxis;
    }
    public int[] getProjectileXAxis() {
        return projectileXAxis;
    }

    public void setWeaponAxis() {
        this.projectileXAxis = new int[1000];
        this.projectileYAxis = new int[1000];
    }
    // confirm is object is alive
    public void setAlive(boolean alive) {
        this.alive = true;
    }
    public boolean isAlive() {
        return alive;
    }

}

