//////////////////////////  GameObject prototype /////////////////////////////////

function GameObject(program, x, y, z, degrees, bounding_cir_rad, modelViewMatrix)  {

    this.x = x;
    this.y = y;
    this.z = z;
    this.degrees = degrees;
    this.ydir    = Math.sin(this.degrees);
    this.xdir    = Math.cos(this.degrees*Math.PI/180.0);
    this.zdir    = Math.sin(this.degrees*Math.PI/180.0);
    this.bounding_cir_rad = bounding_cir_rad;

    // GameObject.prototype.turn = function(degreesRotation) {
	// this.degrees = (this.degrees + degreesRotation) % 360;
	// this.xdir    = Math.cos(this.degrees*Math.PI/180.0);
	// this.zdir    = Math.sin(this.degrees*Math.PI/180.0);
    // };

    GameObject.prototype.move = function(speed) {		// Pass in negative speed for backward motion
	// this.x = this.x + speed * this.xdir;
	    this.z = this.z + speed;
    };

    GameObject.prototype.jump = function(height){
        return this.y + height;
    }

    GameObject.prototype.gravity = function(){
        return this.y - 10.0;
    }

    GameObject.prototype.vwCoords = function(mesh){
        var hitbox = [];
        var xCoords = [];
        var yCoords = [];
        var zCoords = [];
        for (var i = 0; i < mesh.vertices[0].values.length; i+=3)
        {
            xCoords.push(mesh.vertices[0].values[i]);
            yCoords.push(mesh.vertices[0].values[i+1]);
            zCoords.push(mesh.vertices[0].values[i+2]);
        }
        hitbox["xMin"] = Math.min.apply(null, xCoords);
		hitbox["xMax"] = Math.max.apply(null, xCoords);
		hitbox["yMin"] = Math.min.apply(null, yCoords);
		hitbox["yMax"] = Math.max.apply(null, yCoords);
		hitbox["zMin"] = Math.min.apply(null, zCoords);
        hitbox["zMax"] = Math.max.apply(null, zCoords);
       
        return hitbox;
    }
    
};



//////////////////////////  End GameObject prototype /////////////////////////////////
