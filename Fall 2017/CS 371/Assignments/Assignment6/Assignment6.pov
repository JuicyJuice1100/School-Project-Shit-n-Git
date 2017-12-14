// PoVRay 3.7 Scene File " ... .pov"
// author:  ...
// date:    ...
//--------------------------------------------------------------------------
#version 3.7;
global_settings{ assumed_gamma 1.0 }
#default{ finish{ ambient 0.1 diffuse 0.9 }} 
//--------------------------------------------------------------------------
#include "colors.inc"
#include "textures.inc"
#include "glass.inc"
#include "metals.inc"
#include "golds.inc"
#include "stones.inc"
#include "woods.inc"
#include "shapes.inc"
#include "shapes2.inc"
#include "functions.inc"
#include "math.inc"
#include "transforms.inc"   
#include "shapes3.inc"  


   #declare ShowCloud  = no;
   #declare ShowSmoke  = no;
   #declare ShowDust   = no;
   #declare ShowDebris = no;
                                
   #declare FocusSpline =
   spline {
      natural_spline
      -0.50,<0,0,5>,
      +0.10,<0,0,8>,                                                   
      +0.50,<0,0,1>,
      +0.80,<0,0,1>,
      +1.30,<0,0,0>,
   }
   #declare ZoomSpline =
   spline {
      natural_spline
      -0.50,0.70,
      -0.20,0.90,
      +0.20,0.90,
      +0.40,0.70,
      +1.00,0.70
   }
//--------------------------------------------------------------------------
// camera ------------------------------------------------------------------
#declare camera_location = <-30+40*clock,80,-120>;

   camera {
      location camera_location
      angle    12*ZoomSpline(clock).u
      look_at  <0.2,1.6,1.0>+FocusSpline(clock)
   }
// sun ---------------------------------------------------------------------
light_source{<1500,2500,-2500> color White}
// sky ---------------------------------------------------------------------
light_source{ <-1000, 800, 3000> 
              color White
              looks_like{ sphere{ <0,0,0>, 200 
                                  texture{ pigment{ color White*0.9 }
                                           normal { bumps 1.5
                                                    scale 20    }
                                           finish { ambient 0.8   
                                                    diffuse 0.2
                                                    phong 1     }
                                                  
                                         } // end of texture
                                } // end of sphere
                                rotate <0, 0, clock * -90>
                        } //end of looks_like
            } //end of light_source
// sky --------------------------------------------------------------------
  // the dark blue
plane{ <0,1,0>,1 hollow  
       texture{ pigment { color rgb <0.20, 0.20, 1.0> }
                finish  { ambient 0.25 diffuse 0 } 
              }      
       scale 10000}
  // the clouds 
plane{ <0,1,0>,1 hollow  
       texture{pigment{ bozo turbulence clock * 0.76 + .76
                        color_map { [0.5  rgbf<1.0,1.0,1.0,1.0> ]
                                    [0.6  rgb <1.0,1.0,1.0>     ]
                                    [0.65 rgb <1.5,1.5,1.5>     ]
                                    [1.0  rgb <0.5,0.5,0.5>     ] }
                       }
               finish { ambient 0.25 diffuse 0} 
              }      
       scale 500}

// fog ---------------------------------------------------------------------

fog{ fog_type   2
     distance   50
     color      White
     fog_offset 0.1
     fog_alt    2.0
     turbulence 0.8
   }
// ground ------------------------------------------------------------------

// sea ---------------------------------------------------------------------

plane{ <0,1,0>, 0 
       texture{ Polished_Chrome
                normal { crackle 0.15 scale <0.35,clock * 0.25 + 0.25,0.25> turbulence 0.5 } 
                finish { reflection 0.60}
              }
     }
//--------------------------------------------------------------------------
//---------------------------- objects in scene ----------------------------
//--------------------------------------------------------------------------  
//-------------------------------------------------------------------------- 
#declare ball = object{ //Spheroid(  CenterVector,   RadiusVector Rx,Ry,Rz )  
          Spheroid(<-1.50,2.00,-2.00>,       <2.0,1.2,2.5> )
         
         texture{ pigment{ color rgbt<0.75,0.2,0.0,0.7> }
                  finish { phong 1 }
                } // end of texture
         rotate<0,0,clock*-180> translate<-2,-clock * 2,0>  scale<1,1,1>  
       } // ----------------------------------------------------------------
//-------------------------------------------------------------------------- 

                                     // CSG union, add all of shapes 1...N
intersection {
    object{ Pyramid  // base size <-1,-1,-1>,<1,1,1>
            scale <1,1,1>*1
            texture{ pigment{ color rgb<0.96, 0.84, 0.72> }
                     normal { granite 0.5 scale <3,0.15,3>*0.8 turbulence 0}
                     finish { phong 0.1}
                   }
            scale <3,3,3>  rotate<0, 0,0> translate<1 - clock * 2,0,-1>
          } // end of object        
    //----------------------------------------------------------------------------

//----------------------------------------------------------------------------
    // Facetted_Egg( N_Quarter_Segments, N_Radial_Segments ) 
    object{ Facetted_Egg( 7, 12 )
            texture{ pigment{ color rgb<1,1,1>} 
                     finish { phong 0.5}
                   }
            translate <0,1.15,0> scale 2/(1.15+1.55) translate<1-clock * 2,-1,-1>
            
            scale 3
            rotate<0,0,0> 
            translate<0,0,0>
          } // end of object -----------------------------------------------------
    //----------------------------------------------------------------------------
     

}
       
ball

torus { 1.0,0.25 
        texture { pigment{ color rgb<1,0.7,0>}
                  finish { phong 1 reflection { 0.00 metallic 0.50} }
                } // end of texture
        scale <2,2,2> rotate<0,0,0> translate<0,-clock + 1,1>
      } // end of torus  -------------------------------              

torus { 1.0,0.25 
        texture { pigment{ color rgb<1,0,0>}
                  finish { phong 1 reflection { 0.00 metallic 0.50} }
                } // end of texture
        scale <1.5,1.5,1.5> rotate<0,0,0> translate<0,-clock + 1.3,1>
      } // end of torus  -------------------------------              

torus { 1.0,0.25 
        texture { pigment{ color rgb<0,1,0>}
                  finish { phong 1 reflection { 0.00 metallic 0.50} }
                } // end of texture
        scale <1,1,1> rotate<0,0,0> translate<0,-clock + 1.5,1>
      } // end of torus  -------------------------------              

torus { 1.0,0.25 
        texture { pigment{ color rgb<0,0,1>}
                  finish { phong 1 reflection { 0.00 metallic 0.50} }
                } // end of texture
        scale <0.5,0.5,0.5> rotate<0,0,0> translate<0,-clock + 1.7,1>
      } // end of torus  ------------------------------- 
      
// Include particle system include file
// ************************************
   
   #include "particle.inc"
   
// ...and particle element include file.
   #include "expl.inc"
   
   
// FIRST PARTICLE SYSTEM - EXPLOSION A
// ***********************************
   
// Clock options
// *************
   
   #declare particle_start  = 0.0;
   #declare particle_end    = 2.0;
   #declare particle_cyclic = off;
   #declare particle_steps  = 50;
   
// General particle options
// ************************
   
   #declare particle_frequency = 150;
   #declare particle_life      = 1.0;
   #declare particle_lifeturb  = 0.5;
   #declare particle_seed      = 234;
   //#declare particle_maxnumber = 100;
   
// Environment options
// *******************
   
   #declare particle_drag      = 0.3;
   #declare particle_transfer  = 0.0;
   
   #macro particle_gravity  (Clock,Point) 200*y #end
   #macro particle_wind     (Clock,Point) 5*x #end
   
// Emitter options
// ***************
   
   #macro particle_emitter  (Clock) <0,1.3,0> #end
   #macro particle_emitting (Clock) (Clock<0.05) #end
   #macro particle_emitvect (Clock) <0,0,0> #end
   #macro particle_emitturb (Clock) 30.0     #end
   //#macro particle_emitobj  (Clock) object {} #end
   
// Collision options
// *****************
   
   #declare particle_blockobj     = ball
   #declare particle_bounce       = 1.0;
   #declare particle_bounceturb   = 0.0;
   #declare particle_friction     = 0.0;
   #declare particle_bounceoffset = 0.01;
   //#macro particle_killobj    (Clock) object {}    #end
   
// Particle element macro (optional)
// *********************************
   
   #declare exp_size       = 1.0;
   #declare exp_opacity    = 1.0;
   #declare exp_emission   = 2.5;
   #declare exp_smokeshade = 0.4;
   #declare exp_smokeglow  = 1.5;
   #declare exp_smokestart = 0.14;
   #declare exp_fireend    = 0.23;
   #declare exp_ambient    = 0.5;
   
   #macro particle_element() exp_element() #end
   
// Call particle system
// ********************
   
   #if (clock > .3)
      particle_system ("explosion_a")
   #end
   
   
// SECOND PARTICLE SYSTEM - EXPLOSION B
// ************************************
   
   #declare particle_frequency = 60;
   #macro particle_emitting (Clock) on #end
   //#macro particle_emitvect (Clock) <0,0,0> #end
   #macro particle_emitturb (Clock) max(7+5,20-20*Clock) #end
   
   #declare exp_size       = 1.5;
   #declare exp_opacity    = 1.0;
   #declare exp_emission   = 2.5;
   #declare exp_smokeshade = 0.4;
   #declare exp_smokeglow  = 1.5;
   #declare exp_smokestart = 0.14;
   #declare exp_fireend    = 0.23;
   
   #macro particle_element() exp_element() #end
   
// Call particle system
// ********************
   
   #if (clock > 0.3)
      particle_system ("explosion_b")
   #end
   
   
// THIRD PARTICLE SYSTEM - DUST
// ****************************
   
   #declare particle_frequency = 150;
   #declare particle_life      = 2.5*1.5;
   
   #macro particle_emitter  (Clock) <0,0.7,0> #end
   #macro particle_emitting (Clock) (Clock<0.05) #end
   //#macro particle_emitvect (Clock) <0,0,0> #end
   #macro particle_emitturb (Clock) 35 #end
   
   #macro particle_gravity  (Clock,Point) -10*y #end
   
   #declare particle_blockobj    = plane{y,0}
   
   #declare exp_size       = 8.0;
   #declare exp_opacity    = 0.3;
   #declare exp_emission   = 0.0;
   #declare exp_smokeshade = 1.0;
   #declare exp_smokeglow  = 0.0;
   #declare exp_smokestart = 0.00;
   #declare exp_fireend    = 0.00;
   
   #macro particle_element() exp_element() #end
   
// Call particle system
// ********************
   
   #if (ShowSmoke)
      particle_system ("dust")
   #end
   
   
// FOURTH PARTICLE SYSTEM - DEBRIS
// *******************************
   
// Clock options
// *************
   
   #declare particle_start  = 0.0;
   #declare particle_end    = 2.0;
   #declare particle_cyclic = off;
   #declare particle_steps  = 50;
   
// General particle options
// ************************
   
   #declare particle_frequency = 5000;
   #declare particle_life      = 2.0;
   #declare particle_lifeturb  = 0.5;
   #declare particle_seed      = 243;
   #declare particle_maxnumber = 250;
   
// Environment options
// *******************
   
   #declare particle_drag      = 0.0;
   #declare particle_transfer  = 0.0;
   
   #macro particle_gravity  (Clock,Point) -150*y #end
   #macro particle_wind     (Clock,Point) <0,0,0> #end
   
// Emitter options
// ***************
   
   #macro particle_emitter  (Clock) <0,1.3,-0.1> #end
   #macro particle_emitting (Clock) (Clock<0.05) #end
   #macro particle_emitvect (Clock) <0,0,-30>  #end
   #macro particle_emitturb (Clock) 35         #end
   //#macro particle_emitobj  (Clock) object {} #end
   
// Collision options
// *****************
   
   #declare particle_blockobj    = ball
   #declare particle_bounce       = 0.2;
   #declare particle_bounceturb   = 0.0;
   #declare particle_friction     = 1.0;
   #declare particle_bounceoffset = 0.01;
   //#macro particle_killobj    (Clock) object {}    #end
   
// Particle element macro (optional)
// *********************************
   
   #macro particle_element ()
      #local Seed = seed(p_random*1000);
      box {
         -1, 1 scale <0.04,0.04,0.10>*(0.5+1.0*rand(Seed))
         pigment {rgb (<0.70,0.95,1.00>+<0.30,0.05,0.00>*rand(Seed))*(0.2+0.5*rand(Seed))}
         finish {ambient 0.7 diffuse 0.3}
         rotate (p_rotate+p_location.z*(rand(Seed)-0.5))*360*y
         translate p_location
      }
   #end
   
// Call particle system
// ********************
   
   #if (clock > 0.3)
      particle_system ("debris")
   #end