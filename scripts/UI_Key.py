from google.appengine.ext import db

class UI_Key(db.Model):
    id = db.IntegerProperty()
    key = db.StringProperty()
    groud_id = db.IntegerProperty()
    