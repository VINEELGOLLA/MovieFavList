package com.example.moviefavlist.firebase

import com.example.moviefavlist.interfaces.AuthState
import com.example.moviefavlist.interfaces.LoginState
import com.example.moviefavlist.model.user
import com.example.moviefavlist.view.fragments.Login
import com.example.moviefavlist.view.fragments.SignUp
import com.example.moviefavlist.view.fragments.UserDetail
import com.example.moviefavlist.view.fragments.favlist
import com.example.moviekotlinmvvm.pojo.movieslist
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class FirebaseSource {

    companion object{
        var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        //var lol: SignUp = SignUp()
        lateinit var lol: SignUp


        //var  lol2: Login = Login()

        lateinit var lol2: Login


        var reference: DatabaseReference? = null

        var firebaseUser: FirebaseUser? = null

       // var lol3: UserDetail = UserDetail()

        lateinit var lol3: UserDetail


        //var lol4: favlist = favlist()
        lateinit var lol4: favlist


        var usermovies: ArrayList<movieslist>? = ArrayList()



    }




    fun CreateInWithEmailAndPassword(
        email: String,
        password: String,
        phonenumber: String,
        username: String
    ){
        lol = SignUp()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {  task: Task<AuthResult> ->
                if (task.isSuccessful){
                    //lol.onResultRequest(AuthState.SUCCESS)
                    UploadUserDetails(email,phonenumber,username)
                } else if (!task.isSuccessful)  {
                    lol.onResultRequest(
                        AuthState.FAILED
                    )
                }
            }
    }

    fun signInWithEmailAndPassword(email: String, password: String){
        lol2 = Login()
        try {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {  task: Task<AuthResult> ->
                    if (task.isSuccessful){
                        lol2.onResultRequest(
                            AuthState.SUCCESS
                        )
                    } else if (!task.isSuccessful)  {
                        lol2.onResultRequest(
                            AuthState.FAILED
                        )
                    }
                }
        } catch (e: Exception) {
            lol2.onResultRequest(
                AuthState.EXCEPTION
            )
        }
    }

    fun UploadUserDetails(email: String, phonenumber: String, username: String){
        lol = SignUp()

        firebaseUser = mAuth.getCurrentUser()
        val userid = firebaseUser!!.uid
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid).child("details")

        val hashMap: HashMap<String, String> = HashMap()
        hashMap["id"] = userid
        hashMap["username"] = username
        hashMap["email"] = email
        hashMap["phonenumber"] = phonenumber

        reference!!.setValue(hashMap).addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful){
                lol.onResultRequest(
                    AuthState.SUCCESS
                )

            }
        }).addOnFailureListener(OnFailureListener { exception ->
            lol.onResultRequest(
                AuthState.FAILED
            )

        })
    }

    fun Check_loggedin_loggedout(){
        lol2 = Login()
        firebaseUser = mAuth.currentUser
        System.out.println("yoooo")
        System.out.println(mAuth.currentUser)
        if (mAuth.currentUser == null){
            lol2.onResultLoggedinState(
                LoginState.NotLogginin
            )
        }
        else{
            //mAuth.signOut()
            lol2.onResultLoggedinState(
                LoginState.Loggedin
            )
        }
    }

    fun uploadfavroute(list: movieslist){
        firebaseUser = mAuth.getCurrentUser()
        val userid = firebaseUser!!.uid
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid).child("fav").child(list.id.toString())

        reference!!.setValue(list).addOnCompleteListener(OnCompleteListener {
            if(it.isSuccessful){
                System.out.println("success")
            }
        }).addOnFailureListener(OnFailureListener {
            System.out.println("failed")

        })
    }

    fun download_user_details() {
        lol3 = UserDetail()
        firebaseUser = mAuth.getCurrentUser()
        val userid = firebaseUser!!.uid
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid).child("details")

        reference!!.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                lol3.onResultRequestF(p0.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
               // val users: user = dataSnapshot.getValue(user::class.java)!!
                //System.out.println(users.username)

                //lol3.onResultRequestS(users)


                val user: user = dataSnapshot.getValue(user::class.java)!!
                System.out.println(user.toString())
                lol3.onResultRequestS(user)

            }
        })

    }

    fun download_fav_list(){
        lol4 = favlist()
        firebaseUser = mAuth.getCurrentUser()
        val userid = firebaseUser!!.uid
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid).child("fav")

        reference!!.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                usermovies!!.clear()

                for (postSnapshot in dataSnapshot.children) {
                    var get = movieslist()

                    get.id = postSnapshot.child("id").value.toString()
                    get.poster_path = postSnapshot.child("poster_path").value.toString()
                    get.backdrop_path = postSnapshot.child("backdrop_path").value.toString()
                    get.original_title = postSnapshot.child("original_title").value.toString()
                    get.release_date = postSnapshot.child("release_date").value.toString()
                    get.vote_average = postSnapshot.child("vote_average").value.toString()
                    get.overview = postSnapshot.child("overview").value.toString()


        /*            get.copy(id = postSnapshot.child("id").toString(), poster_path = postSnapshot.child("poster_path").toString(),backdrop_path = postSnapshot.child("backdrop_path").toString(),
                        original_title = postSnapshot.child("original_title").toString(), release_date = postSnapshot.child("release_date").toString(), vote_average = postSnapshot.child("vote_average").toString(),
                        overview = postSnapshot.child("overview").toString())*/

                    usermovies!!.add(get)

                }

                System.out.println(usermovies)
                lol4.onResultRequestS(
                    usermovies!!)
            }

        })

    }

    fun deletefav(id: String){
        firebaseUser = mAuth.getCurrentUser()
        val userid = firebaseUser!!.uid
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid).child("fav").child(id)

        reference!!.removeValue()
    }




}
