from google.appengine.ext import db

class UI_Key(db.Model):
    id = db.IntegerProperty()
    groud = db.StringProperty()
    note = db.StringProperty()